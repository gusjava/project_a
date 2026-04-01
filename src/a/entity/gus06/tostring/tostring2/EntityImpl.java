package a.entity.gus06.tostring.tostring2;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170507";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		return toString1(obj);
	}
	
	/*
	* toString -> elem1;elem2;elem3
	*/
		
	public String toString1(Object obj)
	{
		if(obj==null) return "null";
		if(obj instanceof String) return (String) obj;
		
		if(obj instanceof double[]) return toString((double[]) obj);
		if(obj instanceof float[]) return toString((float[]) obj);
		if(obj instanceof int[]) return toString((int[]) obj);
		if(obj instanceof long[]) return toString((long[]) obj);
		if(obj instanceof boolean[]) return toString((boolean[]) obj);
		if(obj instanceof char[]) return toString((char[]) obj);
		if(obj instanceof short[]) return toString((short[]) obj);
		if(obj instanceof byte[]) return toString((byte[]) obj);
		
		if(obj instanceof Object[]) return toString((Object[]) obj);
		if(obj instanceof Set) return toString((Set) obj);
		if(obj instanceof List) return toString((List) obj);
		
		return obj.toString();
	}
	
	
	
	private String toString(double[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(float[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(int[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(long[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(boolean[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(char[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(short[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(byte[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(Object[] dd)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<dd.length;i++) 
		{
			b.append(toString1(dd[i]));
			if(i<dd.length-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String toString(List list)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++) 
		{
			b.append(toString1(list.get(i)));
			if(i<list.size()-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	
	private String toString(Set set)
	{
		if(set.isEmpty()) return "";
		
		StringBuffer b = new StringBuffer();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			b.append(toString1(it.next()));
			b.append(DELIM);
		}
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
