package a.entity.gus06.data.perform.area;

import a.framework.*;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170127";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[]) return area((int[]) obj);
		if(obj instanceof double[]) return area((double[]) obj);
		if(obj instanceof float[]) return area((float[]) obj);
		if(obj instanceof long[]) return area((long[]) obj);
		
		if(obj instanceof Dimension) return area((Dimension) obj);
		if(obj instanceof Point) return area((Point) obj);
		if(obj instanceof Rectangle) return area((Rectangle) obj);
		if(obj instanceof Image) return area((Image) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Long area(int[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return Long.valueOf(o[0]*o[1]);
	}
	
	private Long area(long[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return Long.valueOf(o[0]*o[1]);
	}
	
	private Double area(double[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return Double.valueOf(o[0]*o[1]);
	}
	
	private Double area(float[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return Double.valueOf(o[0]*o[1]);
	}
	
	private Long area(Dimension o)
	{return Long.valueOf(o.height*o.width);}
	
	private Long area(Point o)
	{return Long.valueOf(o.x*o.y);}
	
	private Long area(Rectangle o)
	{return Long.valueOf(o.height*o.width);}
	
	private Long area(Image o)
	{return Long.valueOf(o.getHeight(null)*o.getWidth(null));}
}
