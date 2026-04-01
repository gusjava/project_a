package a.entity.gus06.swing.tree.build.builder1;

import a.framework.*;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeModel;
import javax.swing.Icon;
import javax.swing.tree.TreeCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200104";}
	
	public static final String KEY_ROOT = "root";
	public static final String KEY_ROOT_PROVIDER = "root_provider";
	public static final String KEY_CHILDREN_BUILDER = "children_builder";
	
	public static final String KEY_ICON = "icon";
	public static final String KEY_ICON_BUILDER = "icon_builder";
	public static final String KEY_TITLE_BUILDER = "title_builder";
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);


	private Service actionColEx;
	private Service custUI;

	public EntityImpl() throws Exception
	{
		actionColEx = Outside.service(this,"gus06.swing.tree.cust.action.expandcollapseall");
		custUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		G rootProvider = (G) get1(map,KEY_ROOT_PROVIDER);
		T childrenBuilder = (T) get1(map,KEY_CHILDREN_BUILDER);
		
		T iconBuilder = (T) get0(map,KEY_ICON_BUILDER);
		T titleBuilder = (T) get0(map,KEY_TITLE_BUILDER);
		
		TreeModel1 model = new TreeModel1(rootProvider,childrenBuilder);
		TreeCellRenderer1 renderer = new TreeCellRenderer1(iconBuilder,titleBuilder);
		
		JTree tree = new JTree(model);
		tree.setCellRenderer(renderer);
		
		actionColEx.p(tree);
		custUI.p(tree);
		
		return tree;
	}
	
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	
	private G findRootProvider(Map map) throws Exception
	{
		if(map.containsKey(KEY_ROOT_PROVIDER)) return (G) map.get(KEY_ROOT_PROVIDER);
		if(map.containsKey(KEY_ROOT)) return new WrapG(map.get(KEY_ROOT));
		
		throw new Exception("Root provider not found");
	}
	
	private class WrapG implements G
	{
		private Object data;
		public WrapG(Object data) {this.data = data;}
		public Object g() throws Exception {return data;}
	}
	
	
	
	
	private class TreeModel1 implements TreeModel
	{ 
		private G rootProvider;
		private T childrenBuilder;
		
		public TreeModel1(G rootProvider, T childrenBuilder)
		{
			this.rootProvider = rootProvider;
			this.childrenBuilder = childrenBuilder;
		}

		public Object getRoot()
		{return buildRoot(rootProvider);}
		

		public Object getChild(Object parent, int index)
		{
			List list = buildChildren(childrenBuilder,parent);
			if(list==null || list.size()<=index) return null;
			return list.get(index);
		}
		
		public int getIndexOfChild(Object parent, Object child)
		{
			List list = buildChildren(childrenBuilder,parent);
			if(list==null) return -1;
			return list.indexOf(child);
		}

		public int getChildCount(Object parent)
		{
			List list = buildChildren(childrenBuilder,parent);
			if(list==null) return 0;
			return list.size();
		}

		public boolean isLeaf(Object node)
		{
			List list = buildChildren(childrenBuilder,node);
			return list==null || list.isEmpty();
		}
		

		public void addTreeModelListener(TreeModelListener l) {}
		public void removeTreeModelListener(TreeModelListener l) {}
		public void valueForPathChanged(TreePath path, Object newValue) {}
	}
	
	
	
	
	
	
	private class TreeCellRenderer1 extends JLabel implements TreeCellRenderer
	{
		private T iconBuilder;
		private T titleBuilder;
		
		public TreeCellRenderer1(T iconBuilder, T titleBuilder)
		{
			super();
			setOpaque(true);
			
			this.iconBuilder = iconBuilder;
			this.titleBuilder = titleBuilder;
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
		{
			if(value==null) return reset(); 
			
			setText(buildTitle(titleBuilder,value));
			setIcon(buildIcon(iconBuilder,value));
			setBackground(findBackground(selected));
			return this;
		}
		
		private Component reset()
		{
			setText("");
			setIcon(null);
			setBackground(Color.WHITE);
			return this;
		}
		
		private Color findBackground(boolean selected)
		{
			if(selected) return SELECTION_COLOR;
			return Color.WHITE;
		}
	}
	
	
	
	
	
	private Object buildRoot(G g)
	{
		try{return g.g();}
		catch(Exception e){Outside.err(this,"buildRoot(G)",e);}
		return null;
	}
	
	private List buildChildren(T t, Object parent)
	{
		try{return (List) t.t(parent);}
		catch(Exception e){Outside.err(this,"buildChildren(T,Object)",e);}
		return null;
	}
	
	private String buildTitle(T t, Object value)
	{
		if(t==null) return value.toString();
		try{return (String) t.t(value);}
		catch(Exception e){Outside.err(this,"buildTitle(T,Object)",e);}
		return value.toString();
	}
	
	private Icon buildIcon(T t, Object value)
	{
		if(t==null) return null;
		try{return (Icon) t.t(value);}
		catch(Exception e){Outside.err(this,"buildIcon(T,Object)",e);}
		return null;
	}
}
