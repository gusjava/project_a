package a.entity.gus06.sys.expression1.apply.op2.integer;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	private Service pWrap;
	private Service printStreamToP;
	
	public EntityImpl() throws Exception
	{
		pWrap = Outside.service(this,"gus06.feature.wrap.po.e");
		printStreamToP = Outside.service(this,"gus06.convert.printstreamtop");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String op = (String) obj;
		int number = Integer.parseInt(op);
		return new T1(number);
	}
	
	
	private class T1 implements T
	{
		private int number;
		public T1(int number) {this.number = number;}
		
		public Object t(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			obj = o[0];
		
			if(obj==null) return null;
			
			try
			{
				if(obj instanceof Integer) return Boolean.valueOf(toInt(obj)==number);
				if(obj instanceof Double) return Boolean.valueOf(toDouble(obj)==number);
				
				if(obj instanceof String) return elementAt((String) obj,number);
				if(obj instanceof List) return elementAt((List) obj,number);
				if(obj instanceof Object[]) return elementAt((Object[]) obj,number);
				
				if(obj instanceof boolean[]) return elementAt((boolean[]) obj,number);
				if(obj instanceof byte[]) return elementAt((byte[]) obj,number);
				if(obj instanceof int[]) return elementAt((int[]) obj,number);
				if(obj instanceof double[]) return elementAt((double[]) obj,number);
				if(obj instanceof float[]) return elementAt((float[]) obj,number);
				if(obj instanceof long[]) return elementAt((long[]) obj,number);
				if(obj instanceof short[]) return elementAt((short[]) obj,number);
				if(obj instanceof char[]) return elementAt((char[]) obj,number);
				
				if(obj instanceof Map) return elementAt((Map) obj,number);
				if(obj instanceof Set) return contains((Set) obj,number);
				if(obj instanceof File) return child((File) obj,number);
				
				if(obj instanceof R) return retrieve((R) obj,number);
				if(obj instanceof T) return retrieve((T) obj,number);
				if(obj instanceof F) return retrieve((F) obj,number);
				if(obj instanceof H) return retrieve((H) obj,number);
				if(obj instanceof P) return retrieve((P) obj,number);
				
				if(obj instanceof PrintStream) 
					return retrieve((P) printStreamToP.t(obj),number);
			
				throw new Exception("Unsupported data type: "+obj.getClass().getName());
			}
			catch(Exception e)
			{
				String message = "Failed to apply operator ["+number+"] on object's type "+obj.getClass().getName();
				throw new Exception(message,e);
			}
		}
	}
	
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private double toDouble(Object obj)
	{return Double.parseDouble(""+obj);}
	
	
	
	
	private Object elementAt(String s, int n)
	{
		int size = s.length();
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return ""+s.charAt(n);
	}
	
	private Object elementAt(List l, int n)
	{
		int size = l.size();
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return l.get(n);
	}
	
	private Object elementAt(Object[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return a[n];
	}
	
	private Object elementAt(boolean[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Boolean.valueOf(a[n]);
	}
	
	private Object elementAt(byte[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Byte.valueOf(a[n]);
	}
	
	private Object elementAt(int[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Integer.valueOf(a[n]);
	}
	
	private Object elementAt(double[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Double.valueOf(a[n]);
	}
	
	private Object elementAt(float[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Float.valueOf(a[n]);
	}
	
	private Object elementAt(long[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Long.valueOf(a[n]);
	}
	
	private Object elementAt(short[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return Short.valueOf(a[n]);
	}
	
	private Object elementAt(char[] a, int n)
	{
		int size = a.length;
		if(size==0) return null;
		
		if(n<0) n += size;
		if(n<0 || n>=size) return null;
		return ""+a[n];
	}
	
	private Object elementAt(Map m, int n)
	{
		if(m.containsKey(Integer.valueOf(n)))
			return m.get(Integer.valueOf(n));
		if(m.containsKey(""+n))
			return m.get(""+n);
			
		if(m.containsKey("children") && m.get("children") instanceof List)
		{
			List children = (List) m.get("children");
			return elementAt(children,n);
		}
		return null;
	}
	
	private Boolean contains(Set s, int n)
	{
		if(s.contains(Integer.valueOf(n)))
			return Boolean.TRUE;
		if(s.contains(""+n))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private File child(File dir, int n)
	{
		return new File(dir,""+n);
	}
	
	
	
	
	private Object retrieve(R r, int k) throws Exception
	{
		return r.r(""+k);
	}
	
	private Object retrieve(T t, int k) throws Exception
	{
		try{return t.t(Integer.valueOf(k));} catch(Exception e){}
		try{return t.t(Double.valueOf(k));} catch(Exception e){}
		return t.t(""+k);
	}
	
	private Object retrieve(F f, int k) throws Exception
	{
		try{return Boolean.valueOf(f.f(Integer.valueOf(k)));} catch(Exception e){}
		try{return Boolean.valueOf(f.f(Double.valueOf(k)));} catch(Exception e){}
		return Boolean.valueOf(f.f(""+k));
	}
	
	private Object retrieve(H h, int k) throws Exception
	{
		return Double.valueOf(h.h(toDouble(k)));
	}
	
	private Object retrieve(P p, int k) throws Exception
	{
		return pWrap.t(new Object[]{p,Integer.valueOf(k)});
	}
}