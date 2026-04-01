package a.entity.gus06.collection.comparator.date1;

import a.framework.*;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20240302";}


	private Service extractDate;
	private Service stringToDate;

	public EntityImpl() throws Exception
	{
		extractDate = Outside.service(this,"gus06.string.extract.date1.a");
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
	}
	
	
	public Object g() throws Exception
	{return new Comparator1();}
	
	
	private class Comparator1 implements Comparator
	{
		private Map cache = new HashMap();
		public int compare(Object o1, Object o2)
		{
			List l1 = new ArrayList(getData((String) o1));
			List l2 = new ArrayList(getData((String) o2));
			
			Date d1 = next(l1);
			Date d2 = next(l2);
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
		try{return (List) extractDate.t(s);}
		catch(Exception e)
		{return new ArrayList();}
	}
	
	private Date next(List l)
	{
		try
		{
			if(l.isEmpty()) return new Date();
			return (Date) stringToDate.t(l.remove(0));
		}
		catch(Exception e)
		{return new Date();}
	}
}