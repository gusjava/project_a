package a.entity.gus06.collection.comparator.numerical1;

import a.framework.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160804";}


	private Service extractDouble;

	public EntityImpl() throws Exception
	{extractDouble = Outside.service(this,"gus06.string.extract.double1.a");}
	
	
	public Object g() throws Exception
	{return new Comparator1();}
	
	
	private class Comparator1 implements Comparator
	{
		private Map cache = new HashMap();
		public int compare(Object o1, Object o2)
		{
			List l1 = new ArrayList(getData(""+o1));
			List l2 = new ArrayList(getData(""+o2));
			
			Double d1 = next(l1);
			Double d2 = next(l2);
			int r = d1.compareTo(d2);
			
			while(r==0 && (!l1.isEmpty() || !l2.isEmpty()))
			{
				d1 = next(l1);
				d2 = next(l2);
				r = d1.compareTo(d2);
			}
			return r;
		}
	
		private List getData(String s)
		{
			if(!cache.containsKey(s)) cache.put(s, extract(s));
			return (List) cache.get(s);
		}
	}
	
	private List extract(String s)
	{
		try{return (List) extractDouble.t(s);}
		catch(Exception e)
		{return new ArrayList();}
	}
	
	private Double next(List l)
	{
		if(l.isEmpty()) return Double.valueOf(0);
		return (Double) l.remove(0);
	}
}