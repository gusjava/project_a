package a.entity.gus06.swing.textarea.buildtagbrowser2;

import a.framework.*;
import javax.swing.tree.TreePath;
import java.awt.EventQueue;
import javax.swing.JTree;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180322";}
	
	public static final int DIR_NODES_MAX = 100;


	private Service activationSelect;
	private Service delayTextChange;
	private Service displayChooser;
	private Service confirm;
	private Service clipboard;
	private Service fileDisplay;
	private Service listEquals;
	
	public EntityImpl() throws Exception
	{
		activationSelect = Outside.service(this,"gus06.swing.tree.action.activation.select");
		delayTextChange = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		displayChooser = Outside.service(this,"gus06.data.editor.string.display.dialog");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		clipboard = Outside.service(this,"gus06.clipboard.access");
		fileDisplay = Outside.service(this,"gus06.file.getdisplay");
		listEquals = Outside.service(this,"gus06.list.equals.full");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent textComp = (JTextComponent) o[0];
		char delim = toChar(o[1]);
		
		return new JTree1(textComp,delim);
	}
	
	
	
	private char toChar(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.length()!=1) throw new Exception("Invalid delim length: "+s);
		return s.charAt(0);
	}
	
	
	private class JTree1 extends JTree implements TreeSelectionListener, ActionListener, CaretListener, KeyListener, R, V, F, P
	{
		private TreeModel1 model;
		private Object textCompHolder;
		
		private JTextComponent textComp;
		private Vector positions;
		private Vector paths;
		private TagData data;
		
		private char delim;
		private String head;
		private String defaultIconId;
		private boolean editMode = false;
		private E active;
		
		public JTree1(JTextComponent textComp, char delim) throws Exception
		{
			super();
			active = (E) activationSelect.t(this);
			
			this.textComp = textComp;
			this.delim = delim;
			
			positions = new Vector();
			paths = new Vector();
			data = new TagData(delim);
			
			setRootVisible(false);
			updateTree();
			
			addKeyListener(this);
			addTreeSelectionListener(this);
			textComp.addCaretListener(this);
			textCompHolder = delayTextChange.t(textComp);
			((S) textCompHolder).addActionListener(this);
			
			int n = getRowCount();
			for(int i=0;i<n;i++)
			expandRow(n-1-i);
		}
		
		
		public void actionPerformed(ActionEvent e){updateTree();}
		public void caretUpdate(CaretEvent e) {expandTree();}
	
		public void valueChanged(TreeSelectionEvent e)
		{
			if(isSelectionEmpty()) return;
			newTagSelected();
		}
		
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			boolean ctrl = e.isControlDown();
			
			if(ctrl)
			{
				if(key==KeyEvent.VK_C) performCtrlC();
				if(key==KeyEvent.VK_X) performCtrlX();
				if(key==KeyEvent.VK_V) performCtrlV();
				if(key==KeyEvent.VK_UP) performCtrlUp();
				if(key==KeyEvent.VK_DOWN) performCtrlDown();
				if(key==KeyEvent.VK_RIGHT) performCtrlRight();
				if(key==KeyEvent.VK_LEFT) performCtrlLeft();
				if(key==KeyEvent.VK_DELETE) performDissolve();
			}
			else
			{
				if(key==KeyEvent.VK_F11) debugNode();
				if(key==KeyEvent.VK_F12) shiftEditMode();
				if(key==KeyEvent.VK_ESCAPE) stopEditMode();
				
				if(key==KeyEvent.VK_F1) performCreateChild();
				if(key==KeyEvent.VK_F2) performRename();
				if(key==KeyEvent.VK_F3) performDuplicate();
				if(key==KeyEvent.VK_DELETE) performRemove();
				if(key==KeyEvent.VK_ENTER) performCreateAfter();
				
				if(key==KeyEvent.VK_UP) performUp();
				if(key==KeyEvent.VK_DOWN) performDown();
				if(key==KeyEvent.VK_RIGHT) performRight();
				if(key==KeyEvent.VK_LEFT) performLeft();
			}
		}
		
		
		
		
		private void updateTree()
		{
			try
			{
				String text = textComp.getText();
				defaultIconId = findDefaultIconId(text);
				
				boolean isSame = analyzeStructure(text);
				if(!isSame || model==null) rebuildModel();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"updateTree()",e);}
		}
		
		private String findDefaultIconId(String text)
		{
			if(text.startsWith(">"))
				return text.split("\n")[0].substring(1);
			return null;
		}
		
		private boolean analyzeStructure(String text) throws Exception
		{
			Vector positions1 = new Vector();
			Vector tags1 = new Vector();
			Vector tails1 = new Vector();
			
			StringBuilder bName = new StringBuilder();
			StringBuilder bTail = new StringBuilder();
			StringBuilder bHead = new StringBuilder();
			
			boolean newLine = true;
			int position = 0;
			boolean inHead = true;
			
			int length = text.length();
			for(int i=0;i<length;i++)
			{
				char c = text.charAt(i);
				if(c=='\n')
				{
					if(bName.length()>0)
					{
						tags1.add(bName.toString());
						bName = new StringBuilder();
						positions1.add(Integer.valueOf(position));
					}
					else 
					{
						if(inHead) bHead.append(c);
						else bTail.append(c);
					}
				}
				else if(c==delim)
				{
					if(newLine)
					{
						if(inHead)
						{
							head = bHead.toString();
							inHead = false;
						}
						else
						{
							tails1.add(formatTail(bTail.toString()));
							bTail = new StringBuilder();
						}
						position = i;
						bName.append(c);
					}
					else if(bName.length()>0)
					{
						bName.append(c);
					}
					else
					{
						if(inHead) bHead.append(c);
						else bTail.append(c);
					}
				}
				else
				{
					if(bName.length()>0)
					{
						bName.append(c);
					}
					else
					{
						if(inHead) bHead.append(c);
						else bTail.append(c);
					}
				}
				
				newLine = c=='\n';
			}
			
			if(bName.length()>0)
			{
				tags1.add(bName.toString());
				positions1.add(Integer.valueOf(position));
			}
			else if(bTail.length()>0)
			{
				tails1.add(formatTail(bTail.toString()));
			}
			
			while(tails1.size()<tags1.size()) tails1.add("\n");
			
			boolean isSame = listEquals.f(new List[]{data.getTags(),tags1});
			
			data.init(tags1, tails1);
			positions = positions1;
			
			return isSame;
		}
		
		
		private void rebuildModel() throws Exception
		{
			model = new TreeModel1("root");
			
			Vector paths1 = new Vector();
			for(int i=0;i<data.size();i++)
			{
				String name = data.nameAt(i);
				int level = data.levelAt(i);
				
				if(defaultIconId!=null && !name.contains("#"))
					name = defaultIconId+"#"+name;
				
				TreePath newPath = model.nextNode(level,name);
				if(newPath==null) break;
				paths1.add(newPath);
			}
			paths = paths1;
			setModel(model);
			expandTree();
		}
		
		
		
		private void rebuildText() throws Exception
		{
			Vector positions1 = new Vector();
			StringBuilder b = new StringBuilder();
			b.append(head);
			
			int nb = data.size();
			for(int i=0;i<nb;i++)
			{
				String tag = data.tagAt(i);
				b.append(tag);
				int position = b.length();
				positions1.add(Integer.valueOf(position));
				
				b.append("\n");
				
				String tail = data.tailAt(i);
				b.append(formatTail(tail));
			}
			
			String newText = b.toString();
			if(!textComp.getText().equals(newText))
			((V) textCompHolder).v("silent",newText);
			
			this.positions = positions1;
		}
		
		
		
		
		private String formatTail(String tail)
		{
			return tail.endsWith("\n") ? tail : tail+"\n";
		}
		
		
		
		
		private void expandTree()
		{
			if(positions.isEmpty() || paths.isEmpty()) return;
			
			int pos = textComp.getCaretPosition();
			int targetIndex = 0;
			
			for(int i=0;i<positions.size();i++)
			{
				Integer p = positionAt(i);
				if(p!=null && p.intValue()<=pos) targetIndex = i;
			}
			
			if(targetIndex > paths.size()-1)
				targetIndex = paths.size()-1;
			setSelectionAt(targetIndex);
		}
		
		
		
		
		private class MoveCaretThread extends Thread
		{
			private int p;
			public MoveCaretThread(int p){this.p = p;}
			public void run(){textComp.moveCaretPosition(p);}
		}
		
		private void shiftEditMode()
		{
			try
			{
				editMode = !editMode;
				active.e();
				repaint();
				requestFocusInWindow();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"shiftEditMode()",e);}
		}
		
		
		private void startEditMode()
		{if(!editMode) shiftEditMode();}
		
		
		private void stopEditMode()
		{if(editMode) shiftEditMode();}
		
		
		
		
		private void performCreateChild()
		{
			try
			{
				int index = selectedIndex();
				int level = data.levelAt(index);
			
				int nextTagIndex = index+1;
				boolean done = createAt(level+1, nextTagIndex);
				if(!done) return;
				
				rebuildText();
				rebuildModel();
				setSelectionAt(nextTagIndex);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCreateChild()",e);}
		}
		
		
		
		private void performCreateAfter()
		{
			try
			{
				int index = selectedIndex();
				int level = data.levelAt(index);
				
				int nextTagIndex = data.findBlockEnd(index)+1;
				boolean done = createAt(level, nextTagIndex);
				if(!done) return;
				
				rebuildText();
				rebuildModel();
				setSelectionAt(nextTagIndex);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCreateAfter()",e);}
		}
		
		private void performRename()
		{
			try
			{
				int index = selectedIndex();
				boolean done = renameAt(index);
				if(!done) return;
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performRename()",e);}
		}
		
		private void performDuplicate()
		{
			try
			{
				int index2 = selectedIndex();
				if(index2==0) return;
				
				int index3 = data.findBlockEnd(index2);
				
				for(int i=index2;i<=index3;i++)
				{
					int j = index3-index2+1+i;
					duplicateAtIndex(i,j);
				}
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index3+1);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performDuplicate()",e);}
		}
		
		private void performRemove()
		{
			try
			{
				int index2 = selectedIndex();
				if(index2==0) return;
				
				boolean ok = confirm.f("Please, confirm delete");
				if(!ok) return;
				
				int index3 = data.findBlockEnd(index2);
				
				for(int i=index3;i>=index2;i--)
				data.removeAt(i);
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index2-1);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performRemove()",e);}
		}
		
		private void performCtrlC()
		{
			try
			{
				if(!editMode) return;
				
				int index2 = selectedIndex();
				int index3 = data.findBlockEnd(index2);
				
				copy(index2,index3);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlC()",e);}
		}

		private void performCtrlX()
		{
			try
			{
				if(!editMode) return;
				
				int index2 = selectedIndex();
				if(index2==0) return;
				
				int index3 = data.findBlockEnd(index2);
				
				copy(index2,index3);
				
				for(int i=index3;i>=index2;i--)
				data.removeAt(i);
				
				rebuildText();
				rebuildModel();
				int selectedIndex = data.size() > index2 ? index2 : index2-1;
				setSelectionAt(selectedIndex);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlX()",e);}
		}

		private void performCtrlV()
		{
			try
			{
				if(!editMode) return;
				
				int index = selectedIndex();
				
				Object clip = clipboard.g();
				boolean done = importFromData(index,clip);
				if(!done) return;
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index+1);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlV()",e);}
		}
		
		private void performCtrlUp()
		{
			try
			{
				if(!editMode) return;
				
				int index1 = selectedIndex();
				int index2 = data.findPreviousTagWithSameLevel(index1);
				if(index2!=-1) setSelectionAt(index2);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlUp()",e);}
		}
		
		private void performCtrlDown()
		{
			try
			{
				if(!editMode) return;
				
				int index1 = selectedIndex();
				int index2 = data.findNextTagWithSameLevel(index1);
				if(index2!=-1) setSelectionAt(index2);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlDown()",e);}
		}
		
		private void performCtrlRight()
		{
			try
			{
				if(!editMode) return;
				
				int index1 = selectedIndex();
				if(index1==data.size()-1) return;
				setSelectionAt(index1+1);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlRight()",e);}
		}
		
		private void performCtrlLeft()
		{
			try
			{
				if(!editMode) return;
				
				int index1 = selectedIndex();
				int index2 = data.findParent(index1);
				if(index2!=-1) setSelectionAt(index2);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performCtrlLeft()",e);}
		}
		
		private void performDissolve()
		{
			try
			{
				if(!editMode) return;
				
				int index2 = selectedIndex();
				int index3 = data.findBlockEnd(index2);
				if(index2==index3) return;
				
				boolean ok = confirm.f("Please, confirm dissolve");
				if(!ok) return;
				
				for(int i=index3;i>index2;i--) data.decreaseLevelAt(i);
				data.removeAt(index2);
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index2);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performDissolve()",e);}
		}
		
		private void performUp()
		{
			try
			{
				if(!editMode) return;
				
				// BLOCK 1
				int index1 = selectedIndex();
				// si c'est le premier bloc, on ne peut pas le descendre
				if(index1==0) return;
				int index1End = data.findBlockEnd(index1);
				int level1 = data.levelAt(index1);
				int length1 = index1End-index1+1;
				int index1Parent = data.findParent(index1);
				
				// on cherche le bloc precedent de meme niveau
				
				// BLOCK 2
				int index2 = data.findPreviousTagWithSameLevel(index1);
				if(index2!=-1)
				{
					int index2End = data.findBlockEnd(index2);
					
					// si block 2 est juste apres block 1
					if(index1==index2End+1)
					{
						data.rotate(index2, index1End+1, length1);
						
						rebuildText();
						rebuildModel();
						setSelectionAt(index2);
						return;
					}
				}
				
				// sinon, on prend le parent et on recupere le bloc precedent de meme niveau et on le place juste apres
				
				int index3 = data.findPreviousTagWithSameLevel(index1Parent);
				if(index3!=-1)
				{
					int index3End = data.findBlockEnd(index3);
					data.rotate(index3End+1, index1End+1, length1);
					
					rebuildText();
					rebuildModel();
					setSelectionAt(index3End+1);
				}
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performUp()",e);}
		}
		
		private void performDown()
		{
			try
			{
				if(!editMode) return;
				
				// BLOCK 1
				int index1 = selectedIndex();
				int index1End = data.findBlockEnd(index1);
				int level1 = data.levelAt(index1);
				int length1 = index1End-index1+1;
				
				// si c'est le dernier bloc, on ne peut pas le descendre
				if(index1End==data.size()-1) return;
				
				// s'il possede un frere juste en dessous, on les inverse
				int index2 = index1End+1;
				int index2End = data.findBlockEnd(index2);
				int level2 = data.levelAt(index2);
				int length2 = index2End-index2+1;
				
				if(level1==level2)
				{
					data.rotate(index1, index2End+1, -length1);
					
					rebuildText();
					rebuildModel();
					setSelectionAt(index1+length2);
					return;
				}
				
				// sinon, on prend le parent et on cherche le noeud suivant qui est au meme niveau
				// et si ce noeud existe, on deplace le noeud 1 juste apres
				
				int index1Parent = data.findParent(index1);
				int index3 = data.findNextTagWithSameLevel(index1Parent);
				if(index3!=-1)
				{
					data.rotate(index1, index3+1, -length1);
					
					rebuildText();
					rebuildModel();
					setSelectionAt(index3-length1+1);
				}
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performDown()",e);}
		}
		
		private void performRight()
		{
			try
			{
				if(!editMode) return;
				
				int index2 = selectedIndex();
				if(index2==0) return;
				
				int index1 = data.findPreviousTagWithSameLevel(index2);
				if(index1==-1) return;
				
				int index1Parent = data.findParent(index1);
				int index2Parent = data.findParent(index2);
				if(index1Parent!=index2Parent) return;
				
				int index2End = data.findBlockEnd(index2);
				for(int i=index2;i<=index2End;i++)
				data.increaseLevelAt(i);
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index2);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performRight()",e);}
		}
		
		private void performLeft()
		{
			try
			{
				if(!editMode) return;
				
				int index2 = selectedIndex();
				
				if(index2==0) return;
				if(data.levelAt(index2)<2) return;
				
				int index3 = data.findBlockEnd(index2);
				
				for(int i=index2;i<=index3;i++)
				data.decreaseLevelAt(i);
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index2);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"performLeft()",e);}
		}
		
		private void debugNode()
		{
			try
			{
				if(!editMode) return;
				
				int index1 = selectedIndex();
				if(index1==-1) return;
				
				int index2 = data.findBlockEnd(index1);
				int index3 = data.findPreviousTagWithSameLevel(index1);
				int index4 = data.findNextTagWithSameLevel(index1);
				int index5 = data.findParent(index1);
				
				if(index1==index2)
				{
					data.switchTagEndAt(index1, " [current/blockEnd:"+index1+"]");
				}
				else
				{
					data.switchTagEndAt(index1, " [current:"+index1+"]");
					data.switchTagEndAt(index2, " [blockEnd:"+index2+"]");
				}
				if(index3!=-1) data.switchTagEndAt(index3, " [previousSL:"+index3+"]");
				if(index4!=-1) data.switchTagEndAt(index4, " [nextSL:"+index4+"]");
				if(index5!=-1) data.switchTagEndAt(index5, " [parent:"+index5+"]");
				
				rebuildText();
				rebuildModel();
				setSelectionAt(index1);
				
				if(index1!=index2) expandRow(index2);
				if(index3!=-1) expandRow(index3);
				if(index4!=-1) expandRow(index4);
				if(index5!=-1) expandRow(index5);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"debugNode()",e);}
		}
		
		
		
		private void newTagSelected()
		{
			Integer position = selectedTagPosition();
			if(position==null) return;
			
			int p = position.intValue();
			
			textComp.removeCaretListener(this);
			textComp.moveCaretPosition(textComp.getText().length());
			textComp.addCaretListener(this);
			
			EventQueue.invokeLater(new MoveCaretThread(p));
		}
		
		private Integer selectedTagPosition()
		{
			int index = selectedIndex();
			return positionAt(index);
		}
		
		
		private void duplicateAtIndex(int index1, int index2)
		{
			String tag = data.tagAt(index1);
			String tail = data.tailAt(index1);
			
			data.addAt(index2,tag,tail);
		}
		
		private boolean renameAt(int index) throws Exception
		{
			String name = data.nameAt(index);
			int level = data.levelAt(index);
			
			String newName = (String) displayChooser.t(new String[]{"Please, enter new display",name});
			if(newName==null || newName.equals("")) return false;
			
			String newTag = data.rebuildTag(newName,level);
			data.setTagAt(index, newTag);
			return true;
		}
		
		private boolean createAt(int level, int index) throws Exception
		{
			String newName = (String) displayChooser.t(new String[]{"Please, enter display",""});
			if(newName==null || newName.equals("")) return false;
			
			String newTag = data.rebuildTag(newName, level);
			data.addAt(index, newTag, "\n\n");
			
			return true;
		}
		
		private void copy(int index1, int index2) throws Exception
		{
			StringBuilder b = new StringBuilder();
			for(int i=index1;i<=index2;i++)
			{
				String tag = data.tagAt(i);
				String tail =  data.tailAt(i);
				
				b.append(tag);
				b.append("\n");
				b.append(formatTail(tail));
			}
			clipboard.p(b.toString());
		}
		
		private boolean importFromData(int index, Object data) throws Exception
		{
			if(data==null) return false;
			if(data instanceof String) return importFromText(index, (String) data);
			if(data instanceof List) return importFromFiles(index, (List) data);
			return false;
		}
		
		private boolean importFromText(int index, String s)
		{
			if(s.equals("")) return false;
			if(s.charAt(0)!=delim) return false;
			
			int rootLevel = data.levelAt(index);
			
			String[] lines = s.split("\n",-1);
			
			String line0 = lines[0];
			int offset0 = data.levelFor(line0);
			String name0 = line0.substring(offset0);
			
			int pos = index+1;
			int level = rootLevel+1;
			
			String tag = data.rebuildTag(name0,level);
			StringBuilder bTail = new StringBuilder();
			
			for(int i=1;i<lines.length;i++)
			{
				String line = lines[i];
				int offset = data.levelFor(line);
				if(offset==0) bTail.append(line+"\n");
				else
				{
					data.addAt(pos, tag, bTail.toString());
					tag = null;
					bTail = null;
					pos++;
					
					String name = line.substring(offset);
					int newLevel = rootLevel+1-offset0+offset;
					if(newLevel>level+1 || newLevel<rootLevel) break;
					
					level = newLevel;
					tag = data.rebuildTag(name,newLevel);
					bTail = new StringBuilder();
				}
			}
			
			if(tag!=null)
			{
				data.addAt(pos, tag, bTail.toString());
			}
			return true;
		}
		
		
		private boolean importFromFiles(int index, List list) throws Exception
		{
			int rootLevel = data.levelAt(index);
			int pos = index;
			int posLimit = index+DIR_NODES_MAX;
			
			for(int i=0;i<list.size();i++)
			{
				File file = (File) list.get(i);
				String display = (String) fileDisplay.t(file);
				String newTag = data.rebuildTag(display,rootLevel+1);
				pos++;
			
				data.addAt(pos, newTag, "\n");
				if(pos>=posLimit) return true;
				
				if(file.isDirectory())
					pos = importDirChildren(pos,posLimit,file,rootLevel+1);
				if(pos>=posLimit) return true;
			}
			return true;
		}
		
		private int importDirChildren(int pos, int posLimit, File dir, int level) throws Exception
		{
			if(pos>=posLimit) return pos;
			
			File[] children = dir.listFiles();
			for(int i=0;i<children.length;i++)
			{
				File child = children[i];
				String display = (String) fileDisplay.t(child);
				String newTag = data.rebuildTag(display,level+1);
				pos++;
			
				data.addAt(pos, newTag, "\n");
				if(pos>=posLimit) return pos;
				
				if(child.isDirectory())
					pos = importDirChildren(pos,posLimit,child,level+1);
			}
			return pos;
		}
		
		
		
		
		private boolean isFirstSibling(int index0)
		{
			if(index0==0) return true;
			int level0 = data.levelAt(index0);
			int level1 = data.levelAt(index0-1);
			return level1<level0;
		}
		
		private boolean isLastSibling(int index0)
		{
			int tagNb = data.size();
			int level0 = data.levelAt(index0);
			for(int i=index0+1;i<tagNb;i++)
			{
				int level = data.levelAt(i);
				if(level>level0) return true;
				if(level==level0) return false;
			}
			return true;
		}
	
		private Integer positionAt(int index)
		{
			if(index==-1) return null;
			if(index>=positions.size()) return null;
			return (Integer) positions.get(index);
		}
	
		private TreePath pathAt(int index)
		{
			if(index==-1) return null;
			if(index>=paths.size()) return null;
			return (TreePath) paths.get(index);
		}
		
		private void setSelectionAt(int index)
		{
			TreePath path = pathAt(index);
			
			removeTreeSelectionListener(this);
			if(path==null) clearSelection();
			else setSelectionPath(path);
			if(path!=null && !isFocusOwner()) scrollPathToVisible(path);
			addTreeSelectionListener(this);
		}
		
		private int selectedIndex()
		{
			Object node = getLastSelectedPathComponent();
			if(node==null) return -1;
			return model.getNodeIndex(node);
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("selectedTagPosition")) return selectedTagPosition();
			if(key.equals("delim")) return ""+delim;
			if(key.equals("head")) return head;
			if(key.equals("paths")) return paths;
			if(key.equals("positions")) return positions;
			
			if(key.equals("tags")) return data.getTags();
			if(key.equals("tails")) return data.getTails();
			
			if(key.equals("keys")) return new String[]{
				"selectedTagPosition","delim","tags",
				"positions","tails","head","paths"};
				
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("delim")) {delim = toChar(obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public boolean f(Object obj) throws Exception
		{
			String cmd = (String) obj;
			if(cmd.equals("editMode")) return editMode;
			throw new Exception("Unknown command: "+cmd);
		}
		
		public void p(Object obj) throws Exception
		{
			String cmd = (String) obj;
			
			if(cmd.equals("shiftEditMode")) shiftEditMode();
			else if(cmd.equals("startEditMode")) startEditMode();
			else if(cmd.equals("stopEditMode")) stopEditMode();
			
			else throw new Exception("Unknown command: "+cmd);
		}
	}
}