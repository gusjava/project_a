package a.entity.gus06.data.filter.each.distinct;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160712";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof String)
			return checkDistinct((String) obj);
		if(obj instanceof Collection)
			return checkDistinct((Collection) obj);
		if(obj instanceof Object[])
			return checkDistinct((Object[]) obj);
		if(obj instanceof int[])
			return checkDistinct((int[]) obj);
		if(obj instanceof long[])
			return checkDistinct((long[]) obj);
		if(obj instanceof double[])
			return checkDistinct((double[]) obj);
		if(obj instanceof float[])
			return checkDistinct((float[]) obj);
		if(obj instanceof boolean[])
			return checkDistinct((boolean[]) obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private boolean checkDistinct(String s)
	{
		Set set = new HashSet();
		for(int i=0;i<s.length();i++)
		{
			String element = ""+s.charAt(i);
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(Collection c)
	{
		Set set = new HashSet();
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(Object[] oo)
	{
		Set set = new HashSet();
		for(Object element : oo)
		{
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(int[] oo)
	{
		Set set = new HashSet();
		for(int element : oo)
		{
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(long[] oo)
	{
		Set set = new HashSet();
		for(long element : oo)
		{
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(double[] oo)
	{
		Set set = new HashSet();
		for(double element : oo)
		{
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(float[] oo)
	{
		Set set = new HashSet();
		for(float element : oo)
		{
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
	
	private boolean checkDistinct(boolean[] oo)
	{
		Set set = new HashSet();
		for(boolean element : oo)
		{
			if(set.contains(element)) return false;
			set.add(element);
		}
		return true;
	}
}
