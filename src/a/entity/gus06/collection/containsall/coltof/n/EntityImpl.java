package a.entity.gus06.collection.containsall.coltof.n;

import a.framework.*;
import java.util.Collection;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}
	
	private Service findSet;

	public EntityImpl() throws Exception
	{findSet = Outside.service(this,"gus06.find.set.n");}
	
	public Object t(Object obj) throws Exception
	{return new F1((Collection) obj);}
	
	private class F1 implements F
	{
		private Set set;
		public F1(Collection c) throws Exception
		{set = findSet(c);}
		
		public boolean f(Object obj) throws Exception
		{
			Set set0 = findSet(obj);
			return set.containsAll(set0);
		}
	}
	
	private Set findSet(Object obj) throws Exception
	{return (Set) findSet.t(obj);}
}