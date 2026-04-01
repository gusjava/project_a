package a.entity.gus06.awt.rectangle.multiply;

import a.framework.*;
import java.awt.Rectangle;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20231106";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Rectangle r = (Rectangle) o[0];
		Object value = o[1];
		
		if(value instanceof Number)
		{
			double factor = ((Number) value).doubleValue();
			
			int x1 = (int) (factor * r.x);
			int y1 = (int) (factor * r.y);
			int w1 = (int) (factor * r.width);
			int h1 = (int) (factor * r.height);
			
			r.setBounds(x1,y1,w1,h1);
			return;
		}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Rectangle r = (Rectangle) o[0];
		Object value = o[1];
		
		if(value instanceof Number)
		{
			double factor = ((Number) value).doubleValue();
			
			int x1 = (int) (factor * r.x);
			int y1 = (int) (factor * r.y);
			int w1 = (int) (factor * r.width);
			int h1 = (int) (factor * r.height);
			
			return new Rectangle(x1,y1,w1,h1);
		}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
}