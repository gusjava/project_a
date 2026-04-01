package a.entity.gus06.swing.tree.model.build.frommap.children;

import java.util.List;
import java.util.Map;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161022";}
	
	public static final String KEY_CHILDREN = "children";


	
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
		if(!map.containsKey(KEY_CHILDREN)) return null;
		return (List) map.get(KEY_CHILDREN);
	}
}
