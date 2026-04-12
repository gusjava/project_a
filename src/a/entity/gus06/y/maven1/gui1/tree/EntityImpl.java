package a.entity.gus06.y.maven1.gui1.tree;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.util.List;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import java.awt.Component;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.Icon;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class EntityImpl extends S1 implements Entity, G, V, I, 
	ActionListener, TreeWillExpandListener, TreeSelectionListener, KeyListener {
		
	public String creationDate() {return "20251220";}

	private Icon iconUnknown;
	private Icon iconNode;
	private Icon iconLeaf;

	private JPanel panel;
	private JTree tree;
	
	private Object engine;
	private String selection;

	public EntityImpl() throws Exception
	{
		iconUnknown = (Icon) Outside.resource(this,"icon#dir2_question");
		iconNode = (Icon) Outside.resource(this,"icon#dir2_");
		iconLeaf = (Icon) Outside.resource(this,"icon#dir2");
		
		tree = new JTree(new Model1());
		tree.setRootVisible(false);
		tree.addTreeSelectionListener(this);
		tree.addTreeWillExpandListener(this);
		tree.addKeyListener(this);
		tree.setCellRenderer(new Renderer1());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree), BorderLayout.CENTER);
		
	}
	
	// TreeWillExpandListener
	
	public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException
	{
		Object o = e.getPath().getLastPathComponent();
		if(o instanceof DefaultMutableTreeNode)
		onNodeExpand((DefaultMutableTreeNode)o);
	}

	public void treeWillCollapse(TreeExpansionEvent e) throws ExpandVetoException {}
	
	// KeyListener
	
	public void keyPressed(KeyEvent e)
	{
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B)
		{ctrlBPressed();return;}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	// TreeSelectionListener
	
	public void valueChanged(TreeSelectionEvent e)
	{
		TreePath path = e.getNewLeadSelectionPath();
		selection = pathToSelection(path);
		selected();
	}
	
	// ActionListener
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("loaded()")) {rebuild();return;}
	}
	
	// FEATURES
	
	public Object g() throws Exception
	{return selection;}
	
	public Object i() throws Exception
	{return panel;}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{
			if(engine!=null) ((S)engine).removeActionListener(this);
			engine = obj;
			((S)engine).addActionListener(this);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	// PRIVATE
	
	private List crawl(String path)
	{
		try
		{
			if(engine==null) return null;
			return (List) ((R)engine).r("crawl:"+path);
		}
		catch(Exception e)
		{Outside.err(this,"crawl(String)",e);}
		return null;
	}
	
	private void ctrlBPressed()
	{
		try
		{
			if(engine==null) return;
			((V) engine).v("browse",selection);
		}
		catch(Exception e)
		{Outside.err(this,"ctrlBPressed()",e);}
	}
	
	private class Model1 extends DefaultTreeModel
	{
		public Model1() throws Exception
		{
			super(new DefaultMutableTreeNode(""));
			load((DefaultMutableTreeNode)getRoot(), "");
		}
	
		private void load(DefaultMutableTreeNode node, String path) throws Exception
		{
			node.removeAllChildren();
			node.add(new DefaultMutableTreeNode(null));
			nodeStructureChanged(node);
	
			new SwingWorker<List,Void>()
			{
				protected List doInBackground()
				{return crawl(path);}
	
				protected void done()
				{
					try
					{
						List list = get();
						rebuildNode(node,list);
						nodeStructureChanged(node);
					}
					catch(Exception e){}
				}
			}.execute();
		}
	}
	
	private class Renderer1 extends DefaultTreeCellRenderer
	{
		public Component getTreeCellRendererComponent(JTree t, Object v, boolean s, boolean e, boolean l, int r, boolean f)
		{
			super.getTreeCellRendererComponent(t,v,s,e,l,r,f);
			if(v instanceof DefaultMutableTreeNode)
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)v;
				setIcon(buildIcon(node));
				setText(buildDisplay(node));
			}
			return this;
		}
	}
	
	
	
	private void rebuild()
	{
		try
		{
			tree.setModel(new Model1());
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}
	
	private void rebuildNode(DefaultMutableTreeNode node, List list)
	{
		node.removeAllChildren();
		if(list==null) return;
		for(Object o : list)
		{
			String path = String.valueOf(o);
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(path);
			child.add(new DefaultMutableTreeNode(null));
			node.add(child);
		}
	}
	
	private void onNodeExpand(DefaultMutableTreeNode node)
	{
		try
		{
			Object u = node.getUserObject();
			String path = u==null ? "" : String.valueOf(u);
			((Model1)tree.getModel()).load(node,path);
		}
		catch(Exception e)
		{Outside.err(this,"onNodeExpand(DefaultMutableTreeNode)",e);}
	}
	
	private Icon buildIcon(DefaultMutableTreeNode node)
	{
		if(isPlaceholder(node)) return iconUnknown;
		if(node.getChildCount()==0) return iconLeaf;
		return iconNode;
	}

	private String buildDisplay(DefaultMutableTreeNode node)
	{
		Object u = node.getUserObject();
		if(u==null) return "...";
		return ""+u;
	}
	
	private boolean isPlaceholder(DefaultMutableTreeNode node)
	{
		if(node.getChildCount()!=1) return false;
		DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getChildAt(0);
		return child.getUserObject()==null;
	}
	
	private String pathToSelection(TreePath path)
	{
		if(path==null) return null;
		Object o = path.getLastPathComponent();
		if(!(o instanceof DefaultMutableTreeNode)) return null;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
		Object u = node.getUserObject();
		return u==null ? null : String.valueOf(u);
	}
	
	private void selected()
	{send(this,"selected()");}
}
