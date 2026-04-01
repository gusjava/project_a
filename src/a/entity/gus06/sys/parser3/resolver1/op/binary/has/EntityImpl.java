package a.entity.gus06.sys.parser3.resolver1.op.binary.has;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.size()!=2) throw new Exception("Invalid split for equals operation: "+cut.size());
		
		Object o1 = t.t(cut.get(0));
		Object o2 = t.t(cut.get(1));
		
		if(o1==null || o2==null) return Boolean.FALSE;
		
		if(o1 instanceof String && o2 instanceof String)
		{
			String s1 = (String) o1;
			String s2 = (String) o2;
			return Boolean.valueOf(s1.indexOf(s2)>=0);
		}
		
		if(o1 instanceof List)
		{
			List list = (List) o1;
			return Boolean.valueOf(contains(list,o2));
		}
		
		if(o1 instanceof Set)
		{
			Set set = (Set) o1;
			return Boolean.valueOf(contains(set,o2));
		}
		
		if(o1 instanceof Map)
		{
			Map map = (Map) o1;
			return Boolean.valueOf(contains(map,o2));
		}
		
		if(o1 instanceof Object[])
		{
			Object[] array = (Object[]) o1;
			return Boolean.valueOf(contains(array,o2));
		}
		
		if(o1 instanceof double[] && o2 instanceof Number)
		{
			double[] dd = (double[]) o1;
			double v = ((Number) o2).doubleValue();
			return Boolean.valueOf(found(dd,v));
		}
		
		if(o1 instanceof float[] && o2 instanceof Number)
		{
			float[] dd = (float[]) o1;
			float v = ((Number) o2).floatValue();
			return Boolean.valueOf(found(dd,v));
		}
		
		if(o1 instanceof int[] && o2 instanceof Number)
		{
			int[] dd = (int[]) o1;
			int v = ((Number) o2).intValue();
			return Boolean.valueOf(found(dd,v));
		}
		
		if(o1 instanceof long[] && o2 instanceof Number)
		{
			long[] dd = (long[]) o1;
			long v = ((Number) o2).longValue();
			return Boolean.valueOf(found(dd,v));
		}
		
		throw new Exception("Invalid has operation (o1="+o1+")");
	}
	
	
	
	
	private boolean found(double[] dd, double v)
	{
		for(double d:dd) if(v==d) return true;
		return false;
	}
	
	private boolean found(float[] dd, float v)
	{
		for(float d:dd) if(v==d) return true;
		return false;
	}
	
	private boolean found(int[] dd, int v)
	{
		for(int d:dd) if(v==d) return true;
		return false;
	}
	
	private boolean found(long[] dd, long v)
	{
		for(long d:dd) if(v==d) return true;
		return false;
	}
	
	
	
	
	private boolean contains(Collection c1, Object o2)
	{
		if(o2 instanceof Collection)
			return c1.containsAll((Collection) o2);
		return c1.contains(o2);
	}
	
	
	private boolean contains(Object[] array, Object o2)
	{
		for(Object elem : array) if(equals(elem,o2)) return true;
		return false;
	}
	
	private boolean contains(Map m1, Object o2)
	{
		if(o2 instanceof Collection)
		{
			Iterator it = ((Collection) o2).iterator();
			while(it.hasNext()) if(!m1.containsKey(it.next())) return false;
			return true;
		}
		return m1.containsKey(o2);
	}
	
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
}
