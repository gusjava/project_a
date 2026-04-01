package a.entity.gus06.sys.expression1.apply.op2.long1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190514";}
	
	
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
		long number = Long.parseLong(op);
		return new T1(number);
	}
	
	
	private class T1 implements T
	{
		private long number;
		public T1(long number) {this.number = number;}
		
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
	
	
	
	private Object elementAt(Map m, long n)
	{
		if(m.containsKey(Long.valueOf(n)))
			return m.get(Long.valueOf(n));
		if(m.containsKey(""+n))
			return m.get(""+n);
		return null;
	}
	
	private Boolean contains(Set s, long n)
	{
		if(s.contains(Long.valueOf(n)))
			return Boolean.TRUE;
		if(s.contains(""+n))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private File child(File dir, long n)
	{
		return new File(dir,""+n);
	}
	
	
	
	
	private Object retrieve(R r, long k) throws Exception
	{
		return r.r(""+k);
	}
	
	private Object retrieve(T t, long k) throws Exception
	{
		try{return t.t(Long.valueOf(k));} catch(Exception e){}
		try{return t.t(Double.valueOf(k));} catch(Exception e){}
		return t.t(""+k);
	}
	
	private Object retrieve(F f, long k) throws Exception
	{
		try{return Boolean.valueOf(f.f(Long.valueOf(k)));} catch(Exception e){}
		try{return Boolean.valueOf(f.f(Double.valueOf(k)));} catch(Exception e){}
		return Boolean.valueOf(f.f(""+k));
	}
	
	private Object retrieve(H h, long k) throws Exception
	{
		return Double.valueOf(h.h(toDouble(k)));
	}
	
	private Object retrieve(P p, long k) throws Exception
	{
		return pWrap.t(new Object[]{p,Long.valueOf(k)});
	}
}