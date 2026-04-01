package a.entity.gus06.data.coltree.addinfos1;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161026";}
	
	
	public static final String ISROOT = "isroot";
	public static final String ISLEAF = "isleaf";
	public static final String ISLAST = "islast";
	public static final String ISFIRST = "isfirst";
	public static final String ISLONELY = "islonely";
	
	public static final String DEEP = "deep";
	public static final String POSITION = "position";
	public static final String SIBLINGCOUNT = "siblingcount";
	public static final String CHILDRENCOUNT = "childrencount";
	public static final String TOTALCOUNT = "totalcount";
	
	public static final String PARENT = "parent";
	public static final String UNIQUECHILD = "uniquechild";
	public static final String PREVIOUS = "previous";
	public static final String NEXT = "next";
	
	public static final String CHILDREN = "children";
	public static final String SIBLING = "sibling";
	
	
	
	public void p(Object obj) throws Exception
	{handle((Map) obj,null,null,-1,0);}
	
	
	
	private int handle(Map map, Map parent, List sibling, int position, int deep)
	{
		boolean hasChildren = map.containsKey(CHILDREN);
		boolean hasParent = parent!=null;
		
		map.put(ISROOT,Boolean.valueOf(!hasParent));
		map.put(ISLEAF,Boolean.valueOf(!hasChildren || children(map).isEmpty()));
		map.put(DEEP,Integer.valueOf(deep));
		
		if(hasParent)
		{
			int siblingCount = sibling.size();
			boolean isLast = position == siblingCount-1;
			boolean isFirst = position == 0;
			boolean isLonely = siblingCount == 1;
			
			map.put(PARENT,parent);
			map.put(SIBLING,sibling);
			map.put(POSITION,Integer.valueOf(position));
			map.put(SIBLINGCOUNT,Integer.valueOf(siblingCount));
			map.put(ISLAST,Boolean.valueOf(isLast));
			map.put(ISFIRST,Boolean.valueOf(isFirst));
			map.put(ISLONELY,Boolean.valueOf(isLonely));
			
			if(!isFirst) map.put(PREVIOUS,sibling.get(position-1));
			if(!isLast) map.put(NEXT,sibling.get(position+1));
		}
		
		int total = 1;
		
		if(hasChildren)
		{
			List children = children(map);
			int childrenCount = children.size();
			
			map.put(CHILDRENCOUNT,Integer.valueOf(childrenCount));
			if(childrenCount==1) map.put(UNIQUECHILD,children.get(0));
			
			for(int i=0;i<childrenCount;i++)
			{
				Map child = (Map) children.get(i);
				total += handle(child,map,children,i,deep+1);
			}
		}
		
		map.put(TOTALCOUNT,Integer.valueOf(total));
		return total;
	}
	
	
	private List children(Map map)
	{return (List) map.get(CHILDREN);}
}