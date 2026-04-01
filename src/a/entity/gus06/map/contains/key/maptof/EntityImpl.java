package a.entity.gus06.map.contains.key.maptof;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160120";}
	
	private Service findSet;
	
	public EntityImpl() throws Exception
	{findSet = Outside.service(this,"gus06.find.set");}
	
	public Object t(Object obj) throws Exception
	{return new F1((Map) obj);}
	
	private class F1 implements F
	{
		private Set set;
		public F1(Map m) throws Exception
		{set = findSet(m.keySet());}
		
		public boolean f(Object obj) throws Exception
		{return set.contains(obj);}
	}
	
	private Set findSet(Object obj) throws Exception
	{return (Set) findSet.t(obj);}
}