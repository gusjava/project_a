package a.entity.gus06.swing.tree.holder.setfilter;

import a.framework.*;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import java.util.List;
import java.util.Iterator;
import javax.swing.event.TreeSelectionEvent;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;


public class EntityImpl extends S1 implements Entity, I, P, G, R, V, TreeSelectionListener {

	public String creationDate() {return "20150501";}


	private Service findList;
	
	private TreeModel1 model;
	private JTree tree;
	
	private List list;
	private String delim = ".";



	public EntityImpl() throws Exception
	{
		findList = Outside.service(this,"gus06.find.stringlist.sorted");
		
		model = new TreeModel1();
		tree = new JTree(model);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
	}
	
	
	public Object i() throws Exception
	{return tree;}
	
	
	public void p(Object obj) throws Exception
	{
		list = (List) findList.t(obj);
		if(list==null) model.reset();
		else rebuild();
	}


	
	
	public Object g() throws Exception
	{
		if(tree.isSelectionEmpty()) return null;
		TreePath treePath = tree.getSelectionPath();
		String elem = element(treePath);
		
		Set set = new HashSet();
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			String line = (String) it.next();
			if(line.equals(elem) || line.startsWith(elem+delim))
			set.add(line);
		}
		return set;
	}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("delim")) return delim;
		if(key.equals("keys")) return new String[]{"delim"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("delim"))
		{delim = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	
	private void rebuild()
	{
		try
		{
			model.reset();
			model.nextNode(0,"ROOT");
            
			String[] previousElements = new String[0];
            
			for(int i=0;i<list.size();i++)
			{
				String line = (String)list.get(i);
				String[] elements = line.split(Pattern.quote(delim));
                
				int cSize = findCommonSize(previousElements,elements);
				for(int j=cSize;j<elements.length;j++)
				model.nextNode(j+1,elements[j]);
                
				previousElements = elements;
			}
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}




	private String element(TreePath treePath)
	{
		Object[] path = treePath.getPath();
		StringBuffer b = new StringBuffer(path[1].toString());
		for(int i=2;i<path.length;i++)
		b.append(delim+path[i]);
		return b.toString();
	}
	
	
	
	private int findCommonSize(String[] tab1, String[] tab2)
	{
		int minLength = Math.min(tab1.length,tab2.length);
		for(int i=0;i<minLength;i++)
		if(!tab1[i].equals(tab2[i])) return i;
		return minLength;
	}


	
	
	public void valueChanged(TreeSelectionEvent evt)
	{
		if(tree.isSelectionEmpty()) return;
                selected();
	}
	
	private void selected()
	{send(this,"selected()");}
	
	
	
	
	
	
	
	
	
	
	public class TreeModel1 extends DefaultTreeModel
	{
		private DefaultMutableTreeNode currentNode;
		private int currentLevel;
		private Vector nodes;
    
		public TreeModel1()
		{ 
			super(null);
			currentLevel = -1;
			nodes = new Vector();
		}
    
		public void reset()
		{
			currentNode = null;
			currentLevel = -1;
			nodes = new Vector();
		}
    
		private void nextNode(int level, Object value) throws Exception
		{
			if(level>currentLevel+1)
			throw new Exception("next node level can not be higher than "+currentLevel+1);
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
        
			if(currentNode==null) setRoot(newNode);
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
