package a.entity.gus06.find.intarray;

import a.framework.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.Rectangle;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}


	private Service stringToArray;
	
	public EntityImpl() throws Exception
	{
		stringToArray = Outside.service(this,"gus06.convert.stringtointarray");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof int[]) return obj;
		if(obj instanceof double[]) return handle((double[]) obj);
		if(obj instanceof long[]) return handle((long[]) obj);
		if(obj instanceof float[]) return handle((float[]) obj);
		if(obj instanceof boolean[]) return handle((boolean[]) obj);
		
		if(obj instanceof Set) return handle((Set) obj);
		if(obj instanceof List) return handle((List) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		if(obj instanceof String) return stringToArray.t(obj);
		if(obj instanceof Dimension) return handle((Dimension) obj);
		if(obj instanceof Rectangle) return handle((Rectangle) obj);
		
		if(obj instanceof int[][]) return handle((int[][]) obj);
		
		if(obj instanceof Integer) return handle((Integer) obj);
		if(obj instanceof Long) return handle((Long) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private int[] handle(double[] t)
	{
		int[] n = new int[t.length];
		for(int i=0;i<t.length;i++) n[i] = (int) t[i];
		return n;
	}
	
	private int[] handle(long[] t)
	{
		int[] n = new int[t.length];
		for(int i=0;i<t.length;i++) n[i] = (int) t[i];
		return n;
	}
	
	private int[] handle(float[] t)
	{
		int[] n = new int[t.length];
		for(int i=0;i<t.length;i++) n[i] = (int) t[i];
		return n;
	}
	
	private int[] handle(boolean[] t)
	{
		int[] n = new int[t.length];
		for(int i=0;i<t.length;i++) n[i] = t[i] ? 1 : 0;
		return n;
	}
	
	private int[] handle(Object[] t) throws Exception
	{
		int[] n = new int[t.length];
		for(int i=0;i<t.length;i++) n[i] = toInt(t[i]);
		return n;
	}
	
	private int[] handle(List list) throws Exception
	{
		int[] n = new int[list.size()];
		for(int i=0;i<list.size();i++) n[i] = toInt(list.get(i));
		return n;
	}
	
	private int[] handle(Set set) throws Exception
	{
		return handle(new ArrayList(set));
	}
	
	private int[] handle(int[][] d) throws Exception
	{
		if(d.length==1) return d[0];
		if(d.length>1 && d[0].length==1)
		{
			int l = d.length;
			int[] r = new int[l];
			for(int i=0;i<l;i++) r[i] = d[i][0];
			return r;
		}
		throw new Exception("Invalid array length: "+d.length);
	}
	
	
	private int[] handle(Number d) throws Exception
	{
		return new int[]{d.intValue()};
	}
	
	
	private int[] handle(Dimension dim)
	{
		return new int[]{dim.width,dim.height};
	}
	
	
	private int[] handle(Rectangle rect)
	{
		return new int[]{rect.x, rect.y, rect.width, rect.height};
	}
	
	
	
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof String) return Integer.parseInt((String) obj);
		if(obj instanceof Number) return ((Number) obj).intValue();
		
		throw new Exception("Invalid data type for int conversion: "+obj.getClass().getName());
	}
	
}
