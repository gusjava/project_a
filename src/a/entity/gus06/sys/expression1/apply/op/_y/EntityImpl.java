package a.entity.gus06.sys.expression1.apply.op._y;

import a.framework.*;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Point;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170306";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Rectangle) return toY((Rectangle) obj);
		if(obj instanceof Component) return toY((Component) obj);
		if(obj instanceof Point) return toY((Point) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Integer toY(Rectangle rec)
	{return Integer.valueOf(rec.y);}
	
	private Integer toY(Component c)
	{return Integer.valueOf(c.getY());}
	
	private Integer toY(Point p)
	{return Integer.valueOf(p.y);}
}
