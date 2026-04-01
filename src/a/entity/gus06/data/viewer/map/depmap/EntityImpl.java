package a.entity.gus06.data.viewer.map.depmap;

import a.framework.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;


public class EntityImpl implements Entity, P, I, R {

	public String creationDate() {return "20150312";}


	private Service uiExpCol;
	
	
	private JPanel panel;
	private JTree tree;
	
	private Map map;
	
	
	
	public EntityImpl() throws Exception
	{
		uiExpCol = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons");
		
		tree = new JTree(new TreeModel1());
		tree.setRootVisible(false);
		
		uiExpCol.p(tree);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree),BorderLayout.CENTER);
	}
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		tree.setModel(new TreeModel1());
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("tree")) return tree;
		if(key.equals("keys")) return new String[]{"tree"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	private List findDep(String name)
	{
		if(name==null) return null;
		if(map==null) return null;
		if(!map.containsKey(name)) return null;
		
		return toList(map.get(name));
	}
	
	
	
	private List toList(Object obj)
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Set)
		{
			ArrayList list = new ArrayList((Set) obj);
			Collections.sort(list);
			return list;
		}
		if(obj instanceof String)
		{
			ArrayList list = new ArrayList();
			list.add(obj);
			return list;
		}
		return null;
	}
	
	
	
	
	private class TreeModel1 implements TreeModel
	{
		public static final String ROOT = "*";
		public Object getRoot() {return ROOT;}
	
		public Object getChild(Object node, int index)
		{
			List l = findDep((String) node);
			if(l==null) return null;
			return l.get(index);
		}
	
		public int getChildCount(Object node)
		{
			List l = findDep((String) node);
			if(l==null) return 0;
			return l.size();
		}
	
		public boolean isLeaf(Object node)
		{
			List l = findDep((String) node);
			if(l==null) return true;
			return l.isEmpty();
		}
	
		public int getIndexOfChild(Object node, Object child)
		{
			List l = findDep((String) node);
			if(l==null) return -1;
			return l.indexOf(child);
		}
		
		public void valueForPathChanged(TreePath path, Object newValue) {}
		public void addTreeModelListener(TreeModelListener listener) {}
		public void removeTreeModelListener(TreeModelListener listener) {}
	}
}
