package a.entity.gus06.appli.gusclient1.gui.appdoc.manager.viewer.modules2.tree;

import a.framework.*;
import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.BorderLayout;
import java.util.List;


public class EntityImpl extends S1 implements Entity, I, G, TreeSelectionListener {

	public String creationDate() {return "20140917";}

	public static final String ICONID = "UTIL_javasrc";
	public static final String GYEMMAIN = "GyemMain";
	public static final String GYEMMANAGER = "GyemManager";
	public static final String START_GYEM = "gus06.manager.gus.gyem.";
	

	private Service findDep;
	private Service nameToClasspath;
	private Service buildJTree;

	private ModuleTreeModel model;
	private JPanel panel;
	private JTree tree;



	public EntityImpl() throws Exception
	{
		findDep = Outside.service(this,"gus06.app.jarfile.manager.gyem.module.finddep");
		nameToClasspath = Outside.service(this,"gus06.app.manager.gyem.module.nametoclasspath");
		buildJTree = Outside.service(this,"gus06.swing.tree.build.fromicon");
		
		model = new ModuleTreeModel();
		tree = (JTree) buildJTree.t(ICONID);
		tree.setRootVisible(false);
		tree.setModel(model);
		
		tree.expandPath(tree.getPathForRow(1));
		tree.expandPath(tree.getPathForRow(0));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree),BorderLayout.CENTER);
		
		tree.addTreeSelectionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void valueChanged(TreeSelectionEvent evt)
	{selectionChanged();}


	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	public Object g() throws Exception
	{
		String moduleName = (String) tree.getLastSelectedPathComponent();
		if(moduleName==null) return null;
		
		if(moduleName.equals(GYEMMAIN)) return START_GYEM+GYEMMAIN;
		if(moduleName.equals(GYEMMANAGER)) return START_GYEM+GYEMMANAGER;
		return nameToClasspath.t(moduleName);
	}
	
	
	
	private List findDep(String name)
	{
		try
		{
			if(name==null) return null;
			return (List) findDep.t(name);
		}
		catch(Exception e)
		{Outside.err(this,"findDep(String)",e);}
		return null;
	}
	
	
	
	
	
	
	private class ModuleTreeModel implements TreeModel
	{
		public static final String ROOT = "<>";
		public Object getRoot() {return ROOT;}
	
	
		public Object getChild(Object node, int index)
		{
			if(node.equals(ROOT)) return index==0?GYEMMAIN:GYEMMANAGER;
			
			List l = findDep((String) node);
			if(l==null) return null;
			return l.get(index);
		}
	
		public int getChildCount(Object node)
		{
			if(node.equals(ROOT)) return 2;
			
			List l = findDep((String) node);
			if(l==null) return 0;
			return l.size();
		}
	
		public boolean isLeaf(Object node)
		{
			if(node.equals(ROOT)) return false;
			
			List l = findDep((String) node);
			if(l==null) return true;
			return l.isEmpty();
		}
	
		
		public int getIndexOfChild(Object node, Object child)
		{
			if(node.equals(ROOT)) return child.equals(GYEMMAIN)?0:1;
			
			List l = findDep((String) node);
			if(l==null) return -1;
			return l.indexOf(child);
		}
	
		
		public void valueForPathChanged(TreePath path, Object newValue) {}
		public void addTreeModelListener(TreeModelListener listener) {}
		public void removeTreeModelListener(TreeModelListener listener) {}
	}
}
