package a.entity.gus06.sys.expression1.apply.op._light_speed;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201218";}

	public static final String T = "constant";

	public static final long LIGHT_SPEED = 299792458;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return LIGHT_SPEED;
	}
}