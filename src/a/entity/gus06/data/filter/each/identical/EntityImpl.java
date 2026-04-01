package a.entity.gus06.data.filter.each.identical;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231016";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof String)
			return checkIdentical((String) obj);
		if(obj instanceof Collection)
			return checkIdentical((Collection) obj);
		if(obj instanceof Object[])
			return checkIdentical((Object[]) obj);
		if(obj instanceof int[])
			return checkIdentical((int[]) obj);
		if(obj instanceof long[])
			return checkIdentical((long[]) obj);
		if(obj instanceof double[])
			return checkIdentical((double[]) obj);
		if(obj instanceof float[])
			return checkIdentical((float[]) obj);
		if(obj instanceof boolean[])
			return checkIdentical((boolean[]) obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private boolean checkIdentical(String s)
	{
		if(s.length()<2) return true;
		char c0 = s.charAt(0);
		for(int i=1;i<s.length();i++)
		if(s.charAt(i)!=c0) return false;
		return true;
	}
	
	private boolean checkIdentical(Collection c)
	{
		if(c.size()<2) return true;
		Iterator it = c.iterator();
		Object c0 = it.next();
		while(it.hasNext())
		if(!it.next().equals(c0)) return false;
		return true;
	}
	
	private boolean checkIdentical(Object[] oo)
	{
		if(oo.length<2) return true;
		Object c0 = oo[0];
		for(int i=1;i<oo.length;i++)
		if(!oo[i].equals(c0)) return false;
		return true;
	}
	
	private boolean checkIdentical(int[] oo)
	{
		if(oo.length<2) return true;
		int c0 = oo[0];
		for(int i=1;i<oo.length;i++)
		if(oo[i]!=c0) return false;
		return true;
	}
	
	private boolean checkIdentical(long[] oo)
	{
		if(oo.length<2) return true;
		long c0 = oo[0];
		for(int i=1;i<oo.length;i++)
		if(oo[i]!=c0) return false;
		return true;
	}
	
	private boolean checkIdentical(double[] oo)
	{
		if(oo.length<2) return true;
		double c0 = oo[0];
		for(int i=1;i<oo.length;i++)
		if(oo[i]!=c0) return false;
		return true;
	}
	
	private boolean checkIdentical(float[] oo)
	{
		if(oo.length<2) return true;
		float c0 = oo[0];
		for(int i=1;i<oo.length;i++)
		if(oo[i]!=c0) return false;
		return true;
	}
	
	private boolean checkIdentical(boolean[] oo)
	{
		if(oo.length<2) return true;
		boolean c0 = oo[0];
		for(int i=1;i<oo.length;i++)
		if(oo[i]!=c0) return false;
		return true;
	}
}