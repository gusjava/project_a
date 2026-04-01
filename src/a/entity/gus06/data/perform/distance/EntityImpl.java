package a.entity.gus06.data.perform.distance;

import a.framework.*;
import java.util.Date;
import java.awt.Color;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}


	private Service distance_doubleArray;
	private Service distance_intArray;
	private Service distance_string;
	private Service distance_color;


	public EntityImpl() throws Exception
	{
		distance_doubleArray = Outside.service(this,"gus06.math.tabdouble.distance.euclidean");
		distance_intArray = Outside.service(this,"gus06.math.tabint.distance.euclidean");
		distance_string = Outside.service(this,"gus06.data.compare.string.comparator1.distance");
		distance_color = Outside.service(this,"gus06.awt.color.distance.rgb");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[]) return distance((Object[]) obj);
		if(obj instanceof List) return distance((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object distance(Object[] t) throws Exception
	{
		if(t.length<2) return null;
		if(t.length==2) return distance(t[0],t[1]);
		
		if(t[0] instanceof Number)
		{
			double d = 0;
			for(int i=0;i<t.length-1;i++)
			d+=distanceNum((Number) t[i],(Number) t[i+1]);
			return Double.valueOf(d);
		}
		if(t[0] instanceof Date)
		{
			double d = 0;
			for(int i=0;i<t.length-1;i++)
			d+=distanceDate((Date) t[i],(Date) t[i+1]);
			return Double.valueOf(d);
		}
		if(t[0] instanceof Color)
		{
			double d = 0;
			for(int i=0;i<t.length-1;i++)
			d+=distanceColor((Color) t[i],(Color) t[i+1]);
			return Double.valueOf(d);
		}
		if(t[0] instanceof double[])
		{
			double d = 0;
			for(int i=0;i<t.length-1;i++)
			d+=distanceDoubleTab((double[]) t[i],(double[]) t[i+1]);
			return Double.valueOf(d);
		}
		if(t[0] instanceof int[])
		{
			double d = 0;
			for(int i=0;i<t.length-1;i++)
			d+=distanceIntTab((int[]) t[i],(int[]) t[i+1]);
			return Double.valueOf(d);
		}
		if(t[0] instanceof String)
		{
			double d = 0;
			for(int i=0;i<t.length-1;i++)
			d+=distanceString((String) t[i],(String) t[i+1]);
			return Double.valueOf(d);
		}
		throw new Exception("Invalid data types: "+t[0].getClass().getName());
	}
	
	private Object distance(List l) throws Exception
	{
		if(l.size()<2) return null;
		if(l.size()==2) return distance(l.get(0),l.get(1));
		
		if(l.get(0) instanceof Number)
		{
			double d = 0;
			for(int i=0;i<l.size()-1;i++)
			d+=distanceNum((Number) l.get(i),(Number) l.get(i+1));
			return Double.valueOf(d);
		}
		if(l.get(0) instanceof Date)
		{
			double d = 0;
			for(int i=0;i<l.size()-1;i++)
			d+=distanceDate((Date) l.get(i),(Date) l.get(i+1));
			return Double.valueOf(d);
		}
		if(l.get(0) instanceof Color)
		{
			double d = 0;
			for(int i=0;i<l.size()-1;i++)
			d+=distanceColor((Color) l.get(i),(Color) l.get(i+1));
			return Double.valueOf(d);
		}
		if(l.get(0) instanceof double[])
		{
			double d = 0;
			for(int i=0;i<l.size()-1;i++)
			d+=distanceDoubleTab((double[]) l.get(i),(double[]) l.get(i+1));
			return Double.valueOf(d);
		}
		if(l.get(0) instanceof int[])
		{
			double d = 0;
			for(int i=0;i<l.size()-1;i++)
			d+=distanceIntTab((int[]) l.get(i),(int[]) l.get(i+1));
			return Double.valueOf(d);
		}
		if(l.get(0) instanceof String)
		{
			double d = 0;
			for(int i=0;i<l.size()-1;i++)
			d+=distanceString((String) l.get(i),(String) l.get(i+1));
			return Double.valueOf(d);
		}
		throw new Exception("Invalid data types: "+l.get(0).getClass().getName());
	}
	
	
	
	
	private Object distance(Object t1, Object t2) throws Exception
	{
		if(t1 instanceof Integer && t2 instanceof Integer)
			return distanceInt((Integer) t1, (Integer) t2);
			
		if(t1 instanceof Number && t2 instanceof Number)
			return distanceNum((Number) t1, (Number) t2);
			
		if(t1 instanceof Date && t2 instanceof Date)
			return distanceDate((Date) t1, (Date) t2);
			
		if(t1 instanceof Color && t2 instanceof Color)
			return distanceColor((Color) t1, (Color) t2);
		
		if(t1 instanceof double[] && t2 instanceof double[])
			return distanceDoubleTab((double[]) t1, (double[]) t2);
		
		if(t1 instanceof int[] && t2 instanceof int[])
			return distanceIntTab((int[]) t1, (int[]) t2);
		
		if(t1 instanceof String && t2 instanceof String)
			return distanceString((String) t1, (String) t2);
		
		throw new Exception("Invalid data types: "+t1.getClass().getName()+" and "+t2.getClass().getName());
	}
	
	
	
	
	
	
	
	
	
	private Integer distanceInt(Integer n1, Integer n2)
	{
		int d1 = n1.intValue();
		int d2 = n2.intValue();
		return Integer.valueOf(Math.abs(d1-d2));
	}
	
	private Double distanceNum(Number n1, Number n2)
	{
		double d1 = n1.doubleValue();
		double d2 = n2.doubleValue();
		return Double.valueOf(Math.abs(d1-d2));
	}
	
	private Long distanceDate(Date n1, Date n2)
	{
		long t1 = n1.getTime();
		long t2 = n2.getTime();
		return Long.valueOf(Math.abs(t1-t2));
	}
	
	private Double distanceColor(Color n1, Color n2) throws Exception
	{
		return (Double) distance_color.t(new Color[]{n1,n2});
	}
	
	private Double distanceDoubleTab(double[] n1, double[] n2) throws Exception
	{
		return (Double) distance_doubleArray.t(new Object[]{n1,n2});
	}
	
	private Double distanceIntTab(int[] n1, int[] n2) throws Exception
	{
		return (Double) distance_intArray.t(new Object[]{n1,n2});
	}
	
	private Double distanceString(String n1, String n2) throws Exception
	{
		return (Double) distance_string.t(new String[]{n1,n2});
	}
}
