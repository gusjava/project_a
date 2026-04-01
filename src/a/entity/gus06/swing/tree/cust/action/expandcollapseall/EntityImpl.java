package a.entity.gus06.swing.tree.cust.action.expandcollapseall;

import a.framework.*;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140724";}


	public static KeyStroke ctrl_up = KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK);
	public static KeyStroke ctrl_right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK);
	public static KeyStroke ctrl_left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK);
	


	public void p(Object obj) throws Exception
	{new TreeHolder((JTree) obj);}
	
	
	
	public void expandAll(JTree tree, boolean expand)
	{
		Object root = tree.getModel().getRoot();
		expand(tree,new TreePath(root),expand);
	}
	
	public void expandSelected(JTree tree, boolean expand)
	{
		TreePath[] selected = tree.getSelectionPaths();
		for(int i=0;i<selected.length;i++)
		expand(tree,selected[i],expand);
	}

	private void expand(JTree tree, TreePath parentPath, boolean expand)
	{
		Object node = parentPath.getLastPathComponent();
		if (!tree.getModel().isLeaf(node))
		{
			int n = tree.getModel().getChildCount(node);
			for(int i=0;i<n;i++)
			{
				Object child = tree.getModel().getChild(node,i);
				TreePath path = parentPath.pathByAddingChild(child);
				expand(tree,path,expand);
			}
		}
		if(expand) tree.expandPath(parentPath);
		else tree.collapsePath(parentPath);
	}
	
	
	
	private void selectRoot(JTree tree)
	{
		if(tree.isRootVisible())
		{tree.setSelectionRow(0);return;}
		
		TreePath path = tree.getSelectionPath();
		if(path==null) return;
		while(path.getPathCount()>2)
			path = path.getParentPath();
		tree.setSelectionPath(path);
	}
	
	

	
	
	
	private class TreeHolder
	{
		private JTree tree;
		private ActionMap actionMap;
		private InputMap inputMap;
		
		private Action action_selectRoot;
		private Action action_expandSelected;
		private Action action_collapseSelected;
		
		
		public TreeHolder(JTree tree_)
		{
			this.tree = tree_;
			actionMap = tree.getActionMap();
			inputMap = tree.getInputMap();
			
			action_selectRoot = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {selectRoot(tree);}
			};
			action_expandSelected = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {expandSelected(tree,true);}
			};
			action_collapseSelected = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {expandSelected(tree,false);}
			};
			
			put("selectRoot",ctrl_up,action_selectRoot);
			put("expandSelected",ctrl_right,action_expandSelected);
			put("collapseSelected",ctrl_left,action_collapseSelected);
		}
		
		private void put(String key, KeyStroke c, Action a)
		{
			actionMap.put(key,a);
			inputMap.put(c,key);
		}
	}
}

