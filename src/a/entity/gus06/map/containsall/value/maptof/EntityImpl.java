package a.entity.gus06.map.containsall.value.maptof;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160201";}
	
	private Service findSet;
	
	public EntityImpl() throws Exception
	{findSet = Outside.service(this,"gus06.find.set");}
	
	public Object t(Object obj) throws Exception
	{return new F1((Map) obj);}
	
	private class F1 implements F
	{
		private Set set;
		public F1(Map m) throws Exception
		{set = findSet(m.values());}
		
		public boolean f(Object obj) throws Exception
		{
			Set set0 = findSet(obj);
			return set.containsAll(set0);
		}
	}
	
	private Set findSet(Object obj) throws Exception
	{return (Set) findSet.t(obj);}
}