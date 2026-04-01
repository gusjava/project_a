package a.entity.gus06.sys.parser3.tool.editor.tree.buildmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import a.framework.*;
import java.util.Comparator;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221012";}
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_OTHER = "other";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_INT = "int";
	
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";


	private Comparator entryComparator;
	
	public EntityImpl() throws Exception
	{
		entryComparator = new Comparator() {
			public int compare(Object o1, Object o2)
			{
				Map.Entry e1 = (Map.Entry) o1;
				Map.Entry e2 = (Map.Entry) o2;
				
				Comparable key1 = (Comparable) e1.getKey();
				Comparable key2 = (Comparable) e2.getKey();
				
				return key1.compareTo(key2);
			}
		};
	}

	
	private List toList(Object obj)
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Map) return mapToList((Map) obj);
		if(obj instanceof Map.Entry) return mapEntryToList((Map.Entry) obj);
		if(obj instanceof Object[]) return Arrays.asList((Object[]) obj);
		if(obj instanceof String[]) return Arrays.asList((String[]) obj);
		
		return null;
	}
	
	
	private List mapToList(Map map)
	{
		if(map.containsKey(TYPE)) return valueToList(map);
		
		List list = new ArrayList(map.entrySet());
		Collections.sort(list, entryComparator);
		return list;
	}
	
	
	private List valueToList(Map map)
	{
		Object value = get(map, VALUE);
		if(value instanceof List)
		{
			return (List) value;
		}
		if(value instanceof Map)
		{
			Map m = (Map) value;
			if(m.containsKey(TYPE)) return insideList(value);
			
			List list = new ArrayList(m.entrySet());
			Collections.sort(list, entryComparator);
			return list;
		}
		return null;
	}
	
	
	private List mapEntryToList(Map.Entry entry)
	{
		return toList(entry.getValue());
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private List insideList(Object obj)
	{
		List list = new ArrayList();
		list.add(obj);
		return list;
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		TreeNode1 rootNode = new TreeNode1(null,obj);
		return new DefaultTreeModel(rootNode);
	}
	
	
	
	private class TreeNode1 implements TreeNode, G
	{
		private TreeNode parent;
		private Object data;
		private List list;
		
		public TreeNode1(TreeNode parent, Object data)
		{
			this.parent = parent;
			this.data = data;
			this.list = toList(data);
		}
		
		public Object g() throws Exception
		{return data;}
		
		public Enumeration children()
		{return list!=null ? Collections.enumeration(list) : null;}
		
		public boolean getAllowsChildren()
		{return true;}
		
		public int getChildCount()
		{return list!=null ? list.size() : 0;}
		
		public boolean isLeaf()
		{return list==null || list.isEmpty();}
		
		public TreeNode getParent()
		{return parent;}
		
		public TreeNode getChildAt(int childIndex)
		{
			if(list==null || list.size()<=childIndex) return null;
			Object child = list.get(childIndex);
			return new TreeNode1(this, child);
		}
		
		public int getIndex(TreeNode node)
		{
			if(list==null) return -1;
			return list.indexOf(node);
		}
	}
}