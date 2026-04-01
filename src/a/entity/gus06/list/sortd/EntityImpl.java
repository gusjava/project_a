package a.entity.gus06.list.sortd;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180504";}


	private Service computeDist;
	
	public EntityImpl() throws Exception
	{
		computeDist = Outside.service(this,"gus06.data.perform.distance");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		Object target = o[1];
		
		Collections.sort(input,new Comparator1(target));
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		Object target = o[1];
		
		List output = new ArrayList(input);
		Collections.sort(output,new Comparator1(target));
		return output;
	}
	
	
	private class Comparator1 implements Comparator
	{
		private Object target;
		private Map cache;
		
		public Comparator1(Object target)
		{
			this.target = target;
			cache = new HashMap();
		}
		
		public int compare(Object o1, Object o2)
		{
			
			Comparable r1 = (Comparable) computeDist(o1,target,cache);
			Comparable r2 = (Comparable) computeDist(o2,target,cache);
			
			if(r1==null || r2==null) 
				return ((Comparable) o1).compareTo((Comparable) o2);
			return r1.compareTo(r2);
		}
	}
	
	
	
	private Object computeDist(Object o, Object target, Map cache)
	{
		try
		{
			if(!cache.containsKey(o)) cache.put(o,computeDist.t(new Object[]{o,target}));
			return cache.get(o);
		}
		catch(Exception e)
		{Outside.err(this,"computeDist(Object,Object,Map)",e);}
		return null;
	}
}
