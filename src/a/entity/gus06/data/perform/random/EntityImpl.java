package a.entity.gus06.data.perform.random;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160819";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Double) return Double.valueOf(random(toDouble(obj)));
		if(obj instanceof Long) return Long.valueOf(random(toLong(obj)));
		if(obj instanceof Integer) return Integer.valueOf(random(toInt(obj)));
		
		if(obj instanceof double[]) return Double.valueOf(random((double[]) obj));
		if(obj instanceof long[]) return Long.valueOf(random((long[]) obj));
		if(obj instanceof int[]) return Integer.valueOf(random((int[]) obj));
		if(obj instanceof boolean[]) return Boolean.valueOf(random((boolean[]) obj));
		if(obj instanceof char[]) return new String(new char[]{random((char[]) obj)});
		
		if(obj instanceof Boolean) return Boolean.valueOf(Math.random()<0.5);
		if(obj instanceof String) return random((String) obj);
		if(obj instanceof List) return random((List) obj);
		if(obj instanceof Set) return random((Set) obj);
		if(obj instanceof Map) return random((Map) obj);
		if(obj instanceof Object[]) return random((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int toInt(Object obj)
	{return ((Integer) obj).intValue();}
	
	private double toDouble(Object obj)
	{return ((Double) obj).doubleValue();}
	
	private long toLong(Object obj)
	{return ((Long) obj).longValue();}
	
	
	
	
	private double random(double n)
	{return Math.random()*n;}
	
	
	private int random(int n)
	{return (int) (Math.random()*n);}
	
	
	private long random(long n)
	{return (long) (Math.random()*n);}
	
	
	
	
	
	private double random(double[] n) throws Exception
	{
		if(n.length==0) throw new Exception("Invalid empty array");
		return n[random(n.length)];
	}
	
	private long random(long[] n) throws Exception
	{
		if(n.length==0) throw new Exception("Invalid empty array");
		return n[random(n.length)];
	}
	
	private int random(int[] n) throws Exception
	{
		if(n.length==0) throw new Exception("Invalid empty array");
		return n[random(n.length)];
	}
	
	private boolean random(boolean[] n) throws Exception
	{
		if(n.length==0) throw new Exception("Invalid empty array");
		return n[random(n.length)];
	}
	
	private char random(char[] n) throws Exception
	{
		if(n.length==0) throw new Exception("Invalid empty array");
		return n[random(n.length)];
	}
	
	
	
	private Object random(List list)
	{
		if(list.isEmpty()) return null;
		return list.get(random(list.size()));
	}
	
	
	private Object random(String s)
	{
		if(s.length()==0) return null;
		return ""+s.charAt(random(s.length()));
	}
	
	
	private Object random(Object[] array)
	{
		if(array.length==0) return null;
		return array[random(array.length)];
	}
	
	
	private Object random(Map m)
	{
		if(m.isEmpty()) return null;
		return m.get(random(m.keySet()));
	}
	
	
	private Object random(Set s)
	{
		if(s.isEmpty()) return null;
		
		Object r = null;
		int n = random(s.size());
		Iterator it = s.iterator();
		for(int i=0;i<n;i++) r = it.next();
		return r;
	}
}