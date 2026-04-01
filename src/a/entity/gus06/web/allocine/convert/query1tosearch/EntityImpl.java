package a.entity.gus06.web.allocine.convert.query1tosearch;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191024";}


	private Service apiSearch;
	private Service stringDistance;


	public EntityImpl() throws Exception
	{
		apiSearch = Outside.service(this,"gus06.web.allocine.api.search");
		stringDistance = Outside.service(this,"gus06.data.compare.string.comparator1.distance");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) obj;
		if(query==null) throw new Exception("Invalid null query");
		
		List list = (List) apiSearch.t(query);
		if(list.isEmpty()) list = (List) apiSearch.t(refineQuery(query));
		
		if(list.isEmpty()) return null;
		if(list.size()==1) return list.get(0);
		
		return findMap(list,query);
	}
	
	
	
	
	private String refineQuery(String query)
	{
		return query
			.replaceAll("\\([^\\)]*\\)","")
			.replaceAll("\\{[^\\)]*\\}","")
			.replaceAll("\\[[^\\)]*\\]","").trim();
	}
	
	
	
	private Map findMap(List list, String query) throws Exception
	{
		Map m = new HashMap();
		
		double d = Double.MAX_VALUE;
		for(int i=0;i<list.size();i++)
		{
			Map map0 = (Map) list.get(i);
			double d0 = findDistanceToQuery(map0,query);
			appendToMap(m,""+d0,map0);
			if(d0<d) d = d0;
		}
		
		Set set = (Set) m.get(""+d);
		if(set.size()==1) return (Map) set.iterator().next();
		
		Iterator it = set.iterator();
		Map found = (Map) it.next();
		int year = findYear(found);
		
		while(it.hasNext())
		{
			Map map0 = (Map) it.next();
			int year0 = findYear(map0);
			if(year0>year)
			{
				found = map0;
				year = year0;
			}
		}
		return found;
	}
	
	
	
	private double findDistanceToQuery(Map map0, String query) throws Exception
	{
		double d = Double.MAX_VALUE;
		
		String title = findTitle(map0);
		if(title!=null) d = distanceTo(title,query);
		
		String titleOriginal = findOriginalTitle(map0);
		if(titleOriginal!=null)
		{
			double d1 = distanceTo(titleOriginal,query);
			if(d1<d) d = d1;
		}
		return d;
	}
	
	
	private double distanceTo(String s1, String s2) throws Exception
	{return (double) stringDistance.t(new Object[]{s1,s2});}
	
	
	
	
	private int findYear(Map map)
	{
		String v = get0(map,"productionYear");
		return v==null ? -1 : Integer.parseInt(v);
	}
	
	private String findTitle(Map map)
	{return get0(map,"title");}
	
	private String findOriginalTitle(Map map) throws Exception
	{return get1(map,"originalTitle");}
	
	
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key ["+key+"] not found inside map: "+map);
		return (String) map.get(key);
	}
	
	private String get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	private void appendToMap(Map m, Object key, Object value)
	{
		if(!m.containsKey(key)) m.put(key,new HashSet());
		((Set) m.get(key)).add(value);
	}
}
