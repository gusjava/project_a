package a.entity.gus06.find.booleanarray;

import a.framework.*;
import java.util.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171014";}


	private Service stringToArray;
	
	
	public EntityImpl() throws Exception
	{
		stringToArray = Outside.service(this,"gus06.convert.stringtobooleanarray");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof boolean[]) return obj;
		if(obj instanceof int[]) return handle((int[]) obj);
		if(obj instanceof long[]) return handle((long[]) obj);
		if(obj instanceof double[]) return handle((double[]) obj);
		if(obj instanceof float[]) return handle((float[]) obj);
		
		if(obj instanceof Set) return handle((Set) obj);
		if(obj instanceof List) return handle((List) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		if(obj instanceof String) return stringToArray.t(obj);
		
		if(obj instanceof boolean[][]) return handle((boolean[][]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean convertInt(int t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertLong(long t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertDouble(double t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertFloat(float t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertObj(Object t) throws Exception
	{
		String s = ""+t;
		
		if(s.equals("true")) return true;
		if(s.equals("1")) return true;
		
		if(s.equals("false")) return false;
		if(s.equals("0")) return false;
		
		throw new Exception("Invalid data: "+t);
	}
	
	
	
	
	private boolean[] handle(int[] t) throws Exception
	{
		boolean[] n = new boolean[t.length];
		for(int i=0;i<t.length;i++) n[i] = convertInt(t[i]);
		return n;
	}
	
	private boolean[] handle(long[] t) throws Exception
	{
		boolean[] n = new boolean[t.length];
		for(int i=0;i<t.length;i++) n[i] = convertLong(t[i]);
		return n;
	}
	
	private boolean[] handle(double[] t) throws Exception
	{
		boolean[] n = new boolean[t.length];
		for(int i=0;i<t.length;i++) n[i] = convertDouble(t[i]);
		return n;
	}
	
	private boolean[] handle(float[] t) throws Exception
	{
		boolean[] n = new boolean[t.length];
		for(int i=0;i<t.length;i++) n[i] = convertFloat(t[i]);
		return n;
	}
	
	private boolean[] handle(Object[] t) throws Exception
	{
		boolean[] n = new boolean[t.length];
		for(int i=0;i<t.length;i++) n[i] = convertObj(t[i]);
		return n;
	}
	
	private boolean[] handle(List list) throws Exception
	{
		boolean[] n = new boolean[list.size()];
		for(int i=0;i<list.size();i++) n[i] = convertObj(list.get(i));
		return n;
	}
	
	private boolean[] handle(Set set) throws Exception
	{
		return handle(new ArrayList(set));
	}
	
	private boolean[] handle(boolean[][] d) throws Exception
	{
		if(d.length==1) return d[0];
		if(d.length>1 && d[0].length==1)
		{
			int l = d.length;
			boolean[] r = new boolean[l];
			for(int i=0;i<l;i++) r[i] = d[i][0];
			return r;
		}
		throw new Exception("Invalid array length: "+d.length);
	}
}
