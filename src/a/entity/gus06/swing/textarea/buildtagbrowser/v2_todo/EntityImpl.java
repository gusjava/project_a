package a.entity.gus06.swing.textarea.buildtagbrowser.v2_todo;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150425";}


	private Service parser;
	
	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus06.sys.treeparser1.engine");
	}


	public Object t(Object obj) throws Exception
	{
		return new TagJTree((JTextArea) obj);
	}
	
	
	private class TagJTree extends JTree implements TreeSelectionListener, DocumentListener, CaretListener
	{
		private JTextArea textArea;
		
		public TagJTree(JTextArea textArea)
		{
			super();
			this.textArea = textArea;
			textArea.getDocument().addDocumentListener(this);
			textArea.addCaretListener(this);
		
			setRootVisible(false);
			addTreeSelectionListener(this);
		}
		
		public void insertUpdate(DocumentEvent evt) {updateTree();}
		public void removeUpdate(DocumentEvent evt) {updateTree();}
		public void changedUpdate(DocumentEvent evt) {}
	
		public void caretUpdate(CaretEvent e) {expandTree();}
	
		public void valueChanged(TreeSelectionEvent e)
		{
			if(isSelectionEmpty()) return;
			newTagSelected();
		}
		
		
		
		
		private void newTagSelected()
		{
			
		}
		
		private void expandTree()
		{
			
		}
		
		private void updateTree()
		{
			
		}
	}
}
