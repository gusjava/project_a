package a.entity.gus06.tostring.tostring1;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}
	
	public static final String DELIM = ",";
	
	
	public Object t(Object obj) throws Exception
	{
		return toString1(obj);
	}
	
	/*
	* toString -> [elem1,elem2,elem3] (pour les listes et tableaux)
	* toString -> {elem1,elem2,elem3} (pour les set)
	* toString -> {key1:value1} (pour les map)
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
		if(obj instanceof List) return toString((List) obj);
		if(obj instanceof Set) return toString((Set) obj);
		if(obj instanceof Map) return toString((Map) obj);
		
		return obj.toString();
	}
	
	public String toString2(Object obj)
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
		
		if(obj instanceof Object[]) return toString2((Object[]) obj);
		if(obj instanceof List) return toString2((List) obj);
		if(obj instanceof Set) return toString2((Set) obj);
		if(obj instanceof Map) return toString2((Map) obj);
		
		return obj.toString();
	}
	
	
	
	private String toString(double[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(float[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(int[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(long[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(boolean[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(char[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(short[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	
	private String toString(byte[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(dd[i]);
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	
	// ARRAY
	
	private String toString(Object[] dd)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<dd.length;i++) 
		{
			b.append(toString2(dd[i]));
			if(i<dd.length-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	private String toString2(Object[] dd)
	{
		return "Array("+dd.length+")";
	}
	
	
	// LIST
	
	private String toString(List list)
	{
		StringBuffer b = new StringBuffer("[");
		for(int i=0;i<list.size();i++) 
		{
			b.append(toString2(list.get(i)));
			if(i<list.size()-1) b.append(DELIM);
		}
		b.append("]");
		return b.toString();
	}
	
	private String toString2(List list)
	{
		return "List("+list.size()+")";
	}
	
	
	// SET
	
	private String toString(Set set)
	{
		if(set.isEmpty()) return "{}";
		
		StringBuffer b = new StringBuffer("{");
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			b.append(toString2(it.next()));
			b.append(DELIM);
		}
		b.deleteCharAt(b.length()-1);
		b.append("}");
		return b.toString();
	}
	
	private String toString2(Set set)
	{
		return "Set("+set.size()+")";
	}
	
	
	// MAP
	
	private String toString(Map map)
	{
		if(map.isEmpty()) return "{:}";
		
		StringBuffer b = new StringBuffer("{");
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			b.append(toString2(key));
			b.append(":");
			b.append(toString2(value));
			b.append(DELIM);
		}
		b.deleteCharAt(b.length()-1);
		b.append("}");
		return b.toString();
	}
	
	private String toString2(Map map)
	{
		return "Map("+map.size()+")";
	}
}