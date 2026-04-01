package a.entity.gus06.convert.maptocomparator;

import a.framework.*;
import java.util.Map;
import java.util.Comparator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160511";}
	
	
	public Object t(Object obj) throws Exception
	{return new Comparator1((Map) obj);}
	
	
	
	private class Comparator1 implements Comparator
	{
		private Map m;
		public Comparator1(Map m){this.m = m;}
		
		public int compare(Object o1, Object o2)
		{
			if(m==null) return ((Comparable) o1).compareTo((Comparable) o2);
			
			int v1 = valueFor(m,o1);
			int v2 = valueFor(m,o2);
			
			if(v1==v2) return ((Comparable) o1).compareTo((Comparable) o2);
			return v2-v1;
		}
	}
	
	
	private int valueFor(Map m, Object o)
	{
		if(!m.containsKey(o)) return 0;
		return Integer.parseInt(""+m.get(o));
	}
}
