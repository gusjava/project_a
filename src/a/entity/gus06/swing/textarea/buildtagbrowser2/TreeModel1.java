package a.entity.gus06.swing.textarea.buildtagbrowser2;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Vector;
import javax.swing.tree.TreePath;


public class TreeModel1 extends DefaultTreeModel
{
	private DefaultMutableTreeNode currentNode;
	private int currentLevel;
	private Vector nodes;
	
	public TreeModel1(Object value)
	{ 
		super(new DefaultMutableTreeNode(value));
		currentNode = (DefaultMutableTreeNode)getRoot();
		currentLevel = 0;
		nodes = new Vector();
	}
	
	public TreePath nextNode(int level, Object value) throws Exception
	{
		if(level>currentLevel+1) return null;
		
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