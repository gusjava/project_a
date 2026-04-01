package a.entity.gus06.find.floatarray;

import a.framework.*;
import java.util.*;
import java.awt.geom.Point2D;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	private Service stringToArray;
	
	
	public EntityImpl() throws Exception
	{
		stringToArray = Outside.service(this,"gus06.convert.stringtodoublearray");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof float[]) return obj;
		if(obj instanceof int[]) return handle((int[]) obj);
		if(obj instanceof long[]) return handle((long[]) obj);
		if(obj instanceof double[]) return handle((double[]) obj);
		
		if(obj instanceof Set) return handle((Set) obj);
		if(obj instanceof List) return handle((List) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		if(obj instanceof String) return stringToArray.t(obj);
		if(obj instanceof Point2D) return handle((Point2D) obj);
		
		if(obj instanceof float[][]) return handle((float[][]) obj);
		
		if(obj instanceof Integer) return handle((Integer) obj);
		if(obj instanceof Long) return handle((Long) obj);
		if(obj instanceof Double) return handle((Double) obj);
		if(obj instanceof Float) return handle((Float) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private float[] handle(int[] t)
	{
		float[] n = new float[t.length];
		for(int i=0;i<t.length;i++) n[i] = (float) t[i];
		return n;
	}
	
	private float[] handle(long[] t)
	{
		float[] n = new float[t.length];
		for(int i=0;i<t.length;i++) n[i] = (float) t[i];
		return n;
	}
	
	private float[] handle(double[] t)
	{
		float[] n = new float[t.length];
		for(int i=0;i<t.length;i++) n[i] = (float) t[i];
		return n;
	}
	
	private float[] handle(Object[] t) throws Exception
	{
		float[] n = new float[t.length];
		for(int i=0;i<t.length;i++) n[i] = toFloat(t[i]);
		return n;
	}
	
	private float[] handle(List list) throws Exception
	{
		float[] n = new float[list.size()];
		for(int i=0;i<list.size();i++) n[i] = toFloat(list.get(i));
		return n;
	}
	
	private float[] handle(Set set) throws Exception
	{
		return handle(new ArrayList(set));
	}
	
	private float[] handle(Point2D p)
	{
		return new float[]{(float) p.getX(),(float) p.getY()};
	}
	
	private float[] handle(float[][] d) throws Exception
	{
		if(d.length==1) return d[0];
		if(d.length>1 && d[0].length==1)
		{
			int l = d.length;
			float[] r = new float[l];
			for(int i=0;i<l;i++) r[i] = d[i][0];
			return r;
		}
		throw new Exception("Invalid array length: "+d.length);
	}
	
	
	private float[] handle(Number d) throws Exception
	{
		return new float[]{d.floatValue()};
	}
	
	
	
	
	private float toFloat(Object obj) throws Exception
	{
		if(obj instanceof String) return Float.parseFloat((String) obj);
		if(obj instanceof Number) return ((Number) obj).floatValue();
		
		throw new Exception("Invalid data type for double conversion: "+obj.getClass().getName());
	}
	
}
