package a.entity.gus06.collection.contains.coltof;

import a.framework.*;
import java.util.Collection;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160120";}
	
	private Service findSet;
	
	public EntityImpl() throws Exception
	{findSet = Outside.service(this,"gus06.find.set");}

	public Object t(Object obj) throws Exception
	{return new F1((Collection) obj);}
	
	private class F1 implements F
	{
		private Set set;
		public F1(Collection c) throws Exception
		{set = findSet(c);}
		
		public boolean f(Object obj) throws Exception
		{return set.contains(obj);}
	}
	
	private Set findSet(Object obj) throws Exception
	{return (Set) findSet.t(obj);}
}