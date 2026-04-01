package a.entity.gus06.map.contains.value.maptof.i;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}
	
	private Service findSet;
	
	public EntityImpl() throws Exception
	{findSet = Outside.service(this,"gus06.find.set.i");}
	
	public Object t(Object obj) throws Exception
	{return new F1((Map) obj);}
	
	private class F1 implements F
	{
		private Set set;
		public F1(Map m) throws Exception
		{set = findSet(m.values());}
		
		public boolean f(Object obj) throws Exception
		{
			String s = lower(""+obj);
			return set.contains(s);
		}
	}
	
	private Set findSet(Object obj) throws Exception
	{return (Set) findSet.t(obj);}
	
	private String lower(String s)
	{return s.toLowerCase();}
}