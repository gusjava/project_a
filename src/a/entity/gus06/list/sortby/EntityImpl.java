package a.entity.gus06.list.sortby;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20151116";}


	private Service cache;
	
	public EntityImpl() throws Exception
	{
		cache = Outside.service(this,"gus06.feature.cache.t");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T trans = (T) cache.t(o[1]);
		
		Collections.sort(input,new Comparator1(trans));
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T trans = (T) cache.t(o[1]);
		
		List output = new ArrayList(input);
		Collections.sort(output,new Comparator1(trans));
		return output;
	}
	
	
	private class Comparator1 implements Comparator
	{
		private T t;
		public Comparator1(T t) {this.t = t;}
		
		public int compare(Object o1, Object o2)
		{
			Comparable r1 = (Comparable) trans(t,o1);
			Comparable r2 = (Comparable) trans(t,o2);
			
			if(r1==null || r2==null) 
				return ((Comparable) o1).compareTo((Comparable) o2);
			return r1.compareTo(r2);
		}
	}
	
	
	
	private Object trans(T t, Object o)
	{
		try{return t.t(o);}
		catch(Exception e)
		{Outside.err(this,"trans(T,Object)",e);}
		return null;
	}
}
