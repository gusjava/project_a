package a.entity.gus06.sys.mailclient1.gui.tab1.tree;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.mail.Store;
import javax.mail.Folder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.swing.JTree;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl extends S1 implements Entity, I, P, G, TreeSelectionListener {

	public String creationDate() {return "20201113";}


	private Service buildHolder;
	private Service renderer;
	private Service executeF5;

	private JPanel panel;
	private JTree1 tree;
	
	private Object holder;
	private Store store;


	public EntityImpl() throws Exception
	{
		buildHolder = Outside.service(this,"gus06.sys.mailclient1.tool.folder.buildholder");
		renderer = Outside.service(this,"gus06.sys.mailclient1.gui.tab1.tree.renderer");
		executeF5 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f5");
		
		tree = new JTree1();
		tree.addTreeSelectionListener(this);
		renderer.p(tree);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree),BorderLayout.CENTER);
		
		executeF5.p(new Object[]{tree, (E) this::reload});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return tree.getLastSelectedPathComponent();}
	
	
	
	public void p(Object obj) throws Exception
	{
		holder = obj;
		store = holder!=null ? (Store) ((R)holder).r("storeImap") : null;
		reload();
		tree.setSelectionRow(1);
	}
	
	
	public void valueChanged(TreeSelectionEvent evt)
	{
		if(tree.isSelectionEmpty()) return;
		selected();
	}
	
	
	private void selected()
	{send(this,"selected()");}
	
	
	private void reload()
	{tree.reload();}
	
	
	
	private class JTree1 extends JTree
	{
		public JTree1()
		{
			super(new TreeModel1());
			setExpandsSelectedPaths(true);
		}
		
		public void reload()
		{setModel(new TreeModel1());}
	}
	
	
	private class TreeModel1 implements TreeModel
	{
		private Object root;
		
		public Object getRoot()
		{
			if(root==null) root = buildRoot();
			return root;
		}
		
		public Object getChild(Object node, int index)
		{
			List children = getChildren(node);
			return children!=null ? children.get(index) : null;
		}
	
		public int getChildCount(Object node)
		{
			List children = getChildren(node);
			return children!=null ? children.size() : 0;
		}
	
		public boolean isLeaf(Object node)
		{
			List children = getChildren(node);
			return children==null || children.isEmpty();
		}
	
		
		public int getIndexOfChild(Object node, Object child)
		{
			List children = getChildren(node);
			return children.indexOf(child);
		}
	
		
		public void valueForPathChanged(TreePath path, Object newValue) {}
		public void addTreeModelListener(TreeModelListener listener) {}
		public void removeTreeModelListener(TreeModelListener listener) {}
	}
	
	
	
	
	
	private Object buildRoot()
	{
		try
		{
			if(store==null) return null;
			return buildHolder.t(store.getDefaultFolder());
		}
		catch(Exception e)
		{Outside.err(this,"buildRoot()",e);}
		return null;
	}
	
	
	private List getChildren(Object node)
	{
		try
		{
			if(node==null) return null;
			return (List) ((R) node).r("children");
		}
		catch(Exception e)
		{Outside.err(this,"getChildren(Object)",e);}
		return null;
	}
}