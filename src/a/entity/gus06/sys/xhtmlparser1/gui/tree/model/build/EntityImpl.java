package a.entity.gus06.sys.xhtmlparser1.gui.tree.model.build;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200103";}

	public static final String K_CONTENT = "content";


	
	public Object t(Object obj) throws Exception
	{return new TreeModel0((Map) obj);}

	
	
	private class TreeModel0 implements TreeModel
	{ 
		private Map root;
		
		public TreeModel0(Map root)
		{this.root = root;}

		public Object getRoot()
		{return root;}
		

		public Object getChild(Object parent, int index)
		{
			List list = children(parent);
			if(list==null || list.size()<=index) return null;
			return list.get(index);
		}
		
		public int getIndexOfChild(Object parent, Object child)
		{
			List list = children(parent);
			if(list==null) return -1;
			return list.indexOf(child);
		}

		public int getChildCount(Object parent)
		{
			List list = children(parent);
			if(list==null) return 0;
			return list.size();
		}

		public boolean isLeaf(Object node)
		{
			List list = children(node);
			return list==null || list.isEmpty();
		}
		

		public void addTreeModelListener(TreeModelListener l) {}
		public void removeTreeModelListener(TreeModelListener l) {}
		public void valueForPathChanged(TreePath path, Object newValue) {}
	}
	
	
	private List children(Object parent)
	{
		Map map = (Map) parent;
		if(!map.containsKey(K_CONTENT)) return null;
		return (List) map.get(K_CONTENT);
	}
}
