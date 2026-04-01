package a.entity.gus06.find.point2d;

import a.framework.*;
import java.awt.geom.Point2D;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170425";}


	private Service stringToPoint;
	private Service toDoubleArray;

	public EntityImpl() throws Exception
	{
		stringToPoint = Outside.service(this,"gus06.convert.stringtopoint2d");
		toDoubleArray = Outside.service(this,"gus06.find.doublearray.len2");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Point2D) return obj;
		if(obj instanceof double[]) return fromDoubleArray((double[]) obj);
		if(obj instanceof List) return fromList((List) obj);
		if(obj instanceof String) return (Point2D) stringToPoint.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Point2D fromDoubleArray(double[] v)
	{return new Point2D.Double(v[0],v[1]);}
	
	
	private Point2D fromList(List list) throws Exception
	{
		double[] arr = (double[]) toDoubleArray.t(list);
		return new Point2D.Double(arr[0],arr[1]);
	}
}
