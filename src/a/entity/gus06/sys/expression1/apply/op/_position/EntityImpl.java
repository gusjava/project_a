package a.entity.gus06.sys.expression1.apply.op._position;

import a.framework.*;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170306";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Rectangle) return position((Rectangle) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int[] position(Rectangle rec)
	{return new int[]{rec.x,rec.y};}
}
