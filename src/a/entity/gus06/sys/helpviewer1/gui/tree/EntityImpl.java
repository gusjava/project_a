package a.entity.gus06.sys.helpviewer1.gui.tree;

import a.framework.*;
import javax.swing.JTree;
import java.util.Map;
import javax.swing.tree.TreeModel;
import javax.swing.JScrollPane;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl extends S1 implements Entity, V, I, P, G, TreeSelectionListener {

	public String creationDate() {return "20161022";}

	public static final String KEY_DATA = "data";
	public static final String KEY_CHILDREN = "children";

	private Service parser;
	private Service buildModel;
	private Service renderer;
	private Service expandUI;

	private JTree tree;
	private JScrollPane scroll;
	private TreeModel model;
	

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus06.sys.treeparser1.engine");
		buildModel = Outside.service(this,"gus06.swing.tree.model.build.frommap.children");
		renderer = Outside.service(this,"gus06.sys.helpviewer1.gui.tree.renderer");
		expandUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons");
		
		tree = new JTree();
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		expandUI.p(tree);
		
		scroll = new JScrollPane(tree);
	}
	
	
	public Object g() throws Exception
	{
		if(tree.isSelectionEmpty()) return null;
		return tree.getLastSelectedPathComponent();
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(model!=null) throw new Exception("TreeModel is already initialized");
		
		String content = (String) obj;
		Map map = (Map) parser.t(content);
		model = (TreeModel) buildModel.t(map);
		tree.setModel(model);
		
		renderer.p(tree);
		tree.expandRow(0);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("selectRow"))
		{selectRow(obj);return;}
		
		if(key.equals("selectData"))
		{selectData((F) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void valueChanged(TreeSelectionEvent evt)
	{
		if(tree.isSelectionEmpty()) return;
                selected();
	}
	
	
	private void selected()
	{send(this,"selected()");}
	
	
	private void selectRow(Object obj)
	{
		int row = toInt(obj);
		tree.setSelectionRow(row);
	}
	
	
	private void selectData(F f) throws Exception
	{
		TreePath path = findTreePath(f);
		if(path!=null) tree.setSelectionPath(path);
	}
	
	
	private TreePath findTreePath(F f) throws Exception
	{
		Map root = (Map) model.getRoot();
		List paths = findPaths(root,f);
		if(paths==null) return null;
		return new TreePath(paths.toArray());
	}
	
	private List findPaths(Map node, F f) throws Exception
	{
		List paths = new ArrayList();
		paths.add(node);
		
		if(isValidNode(node,f)) return paths;
		if(!node.containsKey(KEY_CHILDREN)) return null;
		
		List children = (List) node.get(KEY_CHILDREN);
		for(int i=0;i<children.size();i++)
		{
			Map child = (Map) children.get(i);
			List childPaths = findPaths(child,f);
			if(childPaths!=null)
			{
				paths.addAll(childPaths);
				return paths;
			}
		}
		return null;
	}
	
	private boolean isValidNode(Map node, F f) throws Exception
	{return f.f(node.get(KEY_DATA));}
	
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}