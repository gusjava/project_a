package a.entity.gus06.find.longarray;

import a.framework.*;
import java.util.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	private Service stringToArray;
	
	
	public EntityImpl() throws Exception
	{
		stringToArray = Outside.service(this,"gus06.convert.stringtolongarray");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof long[]) return obj;
		if(obj instanceof double[]) return handle((double[]) obj);
		if(obj instanceof int[]) return handle((int[]) obj);
		if(obj instanceof float[]) return handle((float[]) obj);
		if(obj instanceof boolean[]) return handle((boolean[]) obj);
		
		if(obj instanceof Object[]) return handle((Object[]) obj);
		if(obj instanceof String) return stringToArray.t(obj);
		
		if(obj instanceof long[][]) return handle((long[][]) obj);
		
		if(obj instanceof Integer) return handle((Integer) obj);
		if(obj instanceof Long) return handle((Long) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private long[] handle(double[] t)
	{
		long[] n = new long[t.length];
		for(int i=0;i<t.length;i++) n[i] = (long) t[i];
		return n;
	}
	
	private long[] handle(int[] t)
	{
		long[] n = new long[t.length];
		for(int i=0;i<t.length;i++) n[i] = (long) t[i];
		return n;
	}
	
	private long[] handle(float[] t)
	{
		long[] n = new long[t.length];
		for(int i=0;i<t.length;i++) n[i] = (long) t[i];
		return n;
	}
	
	private long[] handle(boolean[] t)
	{
		long[] n = new long[t.length];
		for(int i=0;i<t.length;i++) n[i] = t[i] ? 1 : 0;
		return n;
	}
	
	private long[] handle(Object[] t) throws Exception
	{
		long[] n = new long[t.length];
		for(int i=0;i<t.length;i++) n[i] = toLong(t[i]);
		return n;
	}
	
	private long[] handle(List list) throws Exception
	{
		long[] n = new long[list.size()];
		for(int i=0;i<list.size();i++) n[i] = toLong(list.get(i));
		return n;
	}
	
	private long[] handle(Set set) throws Exception
	{
		return handle(new ArrayList(set));
	}
	
	private long[] handle(long[][] d) throws Exception
	{
		if(d.length==1) return d[0];
		if(d.length>1 && d[0].length==1)
		{
			int l = d.length;
			long[] r = new long[l];
			for(int i=0;i<l;i++) r[i] = d[i][0];
			return r;
		}
		throw new Exception("Invalid array length: "+d.length);
	}
	
	
	private long[] handle(Number d) throws Exception
	{
		return new long[]{d.longValue()};
	}
	
	
	
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof String) return Long.parseLong((String) obj);
		if(obj instanceof Number) return ((Number) obj).longValue();
		
		throw new Exception("Invalid data type for int conversion: "+obj.getClass().getName());
	}
	
}
