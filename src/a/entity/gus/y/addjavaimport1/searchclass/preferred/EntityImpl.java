package a.entity.gus.y.addjavaimport1.searchclass.preferred;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240713";}
	
	public static final String[] STARTS = new String[] {
		"java.lang.",
		"java.util.",
		"java.io.",
		"java.nio.",
		"javax.swing.",
		"java.awt.",
		"java.text.",
		"java.sql."
	};
	
	private Service searchClass;

	public EntityImpl() throws Exception
	{searchClass = Outside.service(this,"gus.y.addjavaimport1.searchclass");}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) obj;
		List list = (List) searchClass.t(query);
			
		if(list==null || list.isEmpty()) return null;
		if(list.size()==1) return list.get(0);
		
		list = keepSmallest(list);
		return keepPreferred(list);
	}
	
	
	
	
	private List keepSmallest(List list)
	{
		List list1 = new ArrayList();
		int d = Integer.MAX_VALUE;
		
		for(Object obj:list)
		{
			String name = (String) obj;
			int d0 = lastPart(name).length();
			
			if(d0<d) {list1.clear();d = d0;}
			if(d0==d) list1.add(name);
		}
		return list1;
	}
	
	
	
	
	
	private String lastPart(String s)
	{
		String[] n = s.split("\\.");
		return n[n.length-1];
	}
	
	
	
	
	
	
	private String keepPreferred(List list)
	{
		String name;
		for(String start:STARTS)
		{
			name = check(list,start);
			if(name!=null) return name;
		}
		return (String) list.get(0);
	}
	
	
	
	
	private String check(List list, String start)
	{
		for(Object obj:list)
		{
			String name = (String) obj;
			if(name.startsWith(start)) return name;
		}
		return null;
	}
}
