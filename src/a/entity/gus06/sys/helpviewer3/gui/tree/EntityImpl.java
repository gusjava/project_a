package a.entity.gus06.sys.helpviewer3.gui.tree;

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
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, V, R, I, P, G, TreeSelectionListener {

	public String creationDate() {return "20250728";}

	public static final String KEY_DATA = "data";
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_ICONPROVIDER = "iconprovider";
	public static final String KEY_EXTERNAL = "external";

	private Service parser;
	private Service buildModel;
	private Service renderer;
	private Service expandUI;

	private JTree tree;
	private JScrollPane scroll;
	private TreeModel model;
	private String content;
	private Map map;
	private R ip;
	private Object external;
	

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus06.sys.treeparser1.engine");
		buildModel = Outside.service(this,"gus06.swing.tree.model.build.frommap.children");
		renderer = Outside.service(this,"gus06.sys.helpviewer3.gui.tree.renderer");
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
		
		Map selected = new HashMap((Map) tree.getLastSelectedPathComponent());
		if(ip!=null) selected.put(KEY_ICONPROVIDER,ip);
		if(external!=null) selected.put(KEY_EXTERNAL,external);
		return selected;
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("ip")) return ip;
		if(key.equals("external")) return external;
		if(key.equals("map")) return map;
		if(key.equals("tree")) return tree;
		if(key.equals("model")) return model;
		if(key.equals("content")) return content;
		
		if(key.equals("keys")) return new String[]{"ip","external","map","tree","model","content"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(model!=null) throw new Exception("TreeModel is already initialized");
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		content = (String) o[0];
		ip = (R) o[1];
		external = o[2];
		
		map = (Map) parser.t(content);
		model = (TreeModel) buildModel.t(map);
		tree.setModel(model);
		
		renderer.p(new Object[]{tree,ip});
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
