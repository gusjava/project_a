package a.entity.gus06.swing.textarea.buildtagbrowser;

import a.framework.*;
import javax.swing.JTextArea;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.EventQueue;
import javax.swing.JTree;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161021";}

	public static char TAG = '@';
	
	
	public Object t(Object obj) throws Exception
	{
		return new TagJTree((JTextArea) obj);
	}
	
	
	
	private class IteratingTreeModel extends DefaultTreeModel
	{
		private DefaultMutableTreeNode currentNode;
		private int currentLevel;
		private Vector nodes;
		
		public IteratingTreeModel(Object value)
		{ 
			super(new DefaultMutableTreeNode(value));
			currentNode = (DefaultMutableTreeNode)getRoot();
			currentLevel = 0;
			nodes = new Vector();
		}
		
		public TreePath nextNode(int level, Object value) throws Exception
		{
			if(level>currentLevel+1)
				throw new Exception("next node level can not be higher than "+currentLevel+1+" (value="+value+")");
			
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(value); 
			
			if(level<currentLevel+1)
			{
				int up = currentLevel-level+1;
				for(int i=0;i<up;i++)
				currentNode = (DefaultMutableTreeNode) currentNode.getParent();
			}
			
			currentNode.add(newNode);
			currentNode = newNode;
			currentLevel = level;
			nodes.add(newNode);
			
			return new TreePath(newNode.getPath());
		}
		
		public int getNodeIndex(Object node)
		{return nodes.indexOf(node);}
	}
	
	
	
	
	private class TagJTree extends JTree implements TreeSelectionListener, DocumentListener, CaretListener, R
	{
		private IteratingTreeModel model;
		
		private JTextArea textArea;
		private Vector tagPositions;
		private Vector tagNames;
		private Vector tagPaths;
		
		private String defaultIconId;
		
		public TagJTree(JTextArea textArea)
		{
			super();
			this.textArea = textArea;
			textArea.getDocument().addDocumentListener(this);
			textArea.addCaretListener(this);
			
			tagPositions = new Vector();
			tagNames = new Vector();
			tagPaths = new Vector();
			
			setRootVisible(false);
			addTreeSelectionListener(this);
			updateTree();
			
			int n = getRowCount();
			for(int i=0;i<n;i++)
			expandRow(n-1-i);
		}
		
		private void updateTree()
		{
			String text = textArea.getText();
			defaultIconId = findDefaultIconId(text);
			
			analyzeStructure(text);
			model = new IteratingTreeModel("root");
			iterateModel();
			setModel(model);
		}
		
		private String findDefaultIconId(String text)
		{
			if(text.startsWith(">"))
				return text.split("\n")[0].substring(1);
			return null;
		}
		
		private void expandTree()
		{
			try
			{
				if(tagPositions.isEmpty()) return;
				
				int p = textArea.getCaretPosition();
				int targetIndex = 0;
				
				for(int i=0;i<tagPositions.size();i++)
				{
					Integer tagPosition = (Integer) tagPositions.get(i);
					if(tagPosition.intValue()<=p) targetIndex = i;
				}
				if(tagPositions.size()!=tagPaths.size())
					throw new Exception("UNEXPECTED ERROR in TagJTree !!! tagPosition.size="+tagPositions.size()+" and tagPaths.size="+tagPaths.size());
				
				if(targetIndex > tagPaths.size()-1)
					targetIndex = tagPaths.size()-1;
				
				TreePath targetPath = (TreePath) tagPaths.get(targetIndex);
				removeTreeSelectionListener(this);
				setSelectionPath(targetPath);
				addTreeSelectionListener(this);
				
				if(!isFocusOwner())
				scrollPathToVisible(targetPath);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"expandTree()",e);}
		}
		
		private void analyzeStructure(String text)
		{
			tagPositions.clear();
			tagNames.clear();
			
			StringBuilder b = new StringBuilder();
			
			boolean newLine = true;
			boolean insideTag = false;
			int position = 0;
			
			
			for(int i=0;i<text.length();i++)
			{
				char c = text.charAt(i);
				
				if(insideTag && c!='\n')
					b.append(c);
				
				if(newLine && c==TAG)
				{
					insideTag = true;
					position = i;
				}
				
				if(c=='\n')
				{
					if(insideTag)
					{
						String tag = b.toString();
						b = new StringBuilder();
						
						tagPositions.add(Integer.valueOf(position));
						tagNames.add(tag);
					}
					newLine = true;
					insideTag = false;
				}
				else newLine = false;
			}
		}
		
		private void iterateModel()
		{
			try
			{
				tagPaths.clear();
				for(int i=0;i<tagNames.size();i++)
				{
					String tag = TAG+(String)tagNames.get(i);
					String[] tab = tag.split(TAG+"+");
					
					String name = "";
					if(tab.length>1)name = tab[1];
					int level = tag.length()-name.length();
					
					if(defaultIconId!=null && !name.contains("#"))
						name = defaultIconId+"#"+name;
					
					TreePath newPath = model.nextNode(level,name);
					tagPaths.add(newPath);
				}
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"iterateModel()",e);}
		}
		
		private Integer selectedTagPosition()
		{
			Object node = getLastSelectedPathComponent();
			int index = model.getNodeIndex(node);
			
			if(tagPositions.size()<index)return null;
			return (Integer)tagPositions.get(index);
		}
		
		private void newTagSelected()
		{
			Integer position = selectedTagPosition();
			if(position==null)return;
			
			int p = position.intValue();   
			textArea.moveCaretPosition(textArea.getText().length());
			EventQueue.invokeLater(new MoveCaretThread(p));
		}
		
		private class MoveCaretThread extends Thread
		{
			private int p;
			public MoveCaretThread(int p){this.p = p;}
			public void run(){textArea.moveCaretPosition(p);}
		}
		
		public void insertUpdate(DocumentEvent evt) {updateTree();}
		public void removeUpdate(DocumentEvent evt) {updateTree();}
		public void changedUpdate(DocumentEvent evt) {}
	
		public void caretUpdate(CaretEvent e) {expandTree();}
	
		public void valueChanged(TreeSelectionEvent e)
		{
			if(isSelectionEmpty())return;
			newTagSelected();
		}
	
		public Object r(String key) throws Exception
		{
			if(key.equals("selectedTagPosition")) return selectedTagPosition();
			if(key.equals("keys")) return new String[]{"selectedTagPosition"};
			throw new Exception("Unknown key: "+key);
		}
	}
}
