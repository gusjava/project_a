package a.entity.gus06.map.contains.key.maptof.n;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}
	
	private Service normalize;
	private Service findSet;

	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		findSet = Outside.service(this,"gus06.find.set.n");
	}
	
	public Object t(Object obj) throws Exception
	{return new F1((Map) obj);}
	
	private class F1 implements F
	{
		private Set set;
		public F1(Map m) throws Exception
		{set = findSet(m.keySet());}
		
		public boolean f(Object obj) throws Exception
		{
			String s = normalize(""+obj);
			return set.contains(s);
		}
	}
	
	
	private Set findSet(Object obj) throws Exception
	{return (Set) findSet.t(obj);}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
}