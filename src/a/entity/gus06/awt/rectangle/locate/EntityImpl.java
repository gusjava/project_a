package a.entity.gus06.awt.rectangle.locate;

import a.framework.*;
import java.awt.Rectangle;
import java.awt.Point;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170306";}


	private Service stringToPoint;
	
	public EntityImpl() throws Exception
	{
		stringToPoint = Outside.service(this,"gus06.convert.stringtopoint");
	}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Rectangle r = (Rectangle) o[0];
		Object value = o[1];
		
		if(value instanceof Integer)
		{
			int dep = ((Integer) value).intValue();
			r.setLocation(dep,dep);
			return;
		}
		if(value instanceof int[])
		{
			int[] dep = (int[]) value;
			r.setLocation(dep[0],dep[1]);
			return;
		}
		if(value instanceof String)
		{
			Point p = (Point) stringToPoint.t(value);
			r.setLocation(p);
			return;
		}
		if(value instanceof Point)
		{
			Point p = (Point) value;
			r.setLocation(p);
			return;
		}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
}
