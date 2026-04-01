package a.entity.gus06.map.build.sortedvalues.bykey.t.inv;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220628";}


	private Service cache;
	
	public EntityImpl() throws Exception
	{
		cache = Outside.service(this,"gus06.feature.cache.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		T trans = (T) cache.t(o[1]);
		
		List keys = new ArrayList(input.keySet());
		Collections.sort(keys,new Comparator1(trans));
		
		List values = new ArrayList();
		for(Object key:keys) values.add(input.get(key));
		
		return values;
	}
	
	
	
	private class Comparator1 implements Comparator
	{
		private T t;
		
		public Comparator1(T t)
		{
			this.t = t;
		}
		
		public int compare(Object key1, Object key2)
		{
			Comparable r1 = (Comparable) trans(t,key1);
			Comparable r2 = (Comparable) trans(t,key2);
			
			return r2.compareTo(r1);
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