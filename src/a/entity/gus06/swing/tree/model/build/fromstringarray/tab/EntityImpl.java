package a.entity.gus06.swing.tree.model.build.fromstringarray.tab;

import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import a.framework.*;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20150609";}

	private String delim = "\t";
	

	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("delim"))
		{delim = (String)obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}



	
	public Object t(Object obj) throws Exception
	{
		String[] lines = (String[])obj;
		TreeModel0 model = new TreeModel0();
		
		for(int i=0;i<lines.length;i++)
		{
			String tag = lines[i];
			
			int level = 0;
			while(tag.startsWith(delim))
			{tag = tag.substring(1);level++;}
			
			addTag(model,tag,level,i);
		}
		return model;
	}
	
	
	
	
	private void addTag(TreeModel0 model, String value, int level, int lineIndex) throws Exception
	{
		try{model.nextNode(level,value);}
		catch(Exception e)
		{
			String message = "Failed to add tag to tree model at line index:"+lineIndex+" (level="+level+" value="+value+")";
			throw new Exception(message,e);
		}
	}


	
	
	private class TreeModel0 extends DefaultTreeModel
	{ 
		private DefaultMutableTreeNode currentNode;
		private int currentLevel;
		private Vector nodes;
		
		public TreeModel0()
		{ 
			super(null);
			currentNode = null;
			currentLevel = -1;
			nodes = new Vector();
		}
		
		public void nextNode(int level, Object value) throws Exception
		{
			if(level>currentLevel+1)
				throw new Exception("Next node (level="+level+" value="+value+") can not be added for current level="+currentLevel);
			registerNode(level,new DefaultMutableTreeNode(value)); 
		}
		
		private void registerNode(int level, DefaultMutableTreeNode newNode)
		{
			if(level<currentLevel+1)
			{
				int up = currentLevel-level+1;
				for(int i=0;i<up;i++)
					currentNode = (DefaultMutableTreeNode)currentNode.getParent();
			}
			
			if(currentNode==null)
				setRoot(newNode);
			else currentNode.add(newNode);
			nodeStructureChanged(currentNode);
			
			currentNode = newNode;
			currentLevel = level;
			nodes.add(newNode); 
		}
		
		public int getNodeIndex(Object node)
		{return nodes.indexOf(node);}
	}
}
