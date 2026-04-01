package a.entity.gus06.sys.expression1.apply.op._c_array9;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180504";}

	public static final String T = "constant";

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return new Color[]{
			Color.BLUE,
			Color.RED,
			Color.GREEN,
			Color.ORANGE,
			Color.CYAN,
			Color.YELLOW,
			Color.MAGENTA,
			Color.GRAY,
			Color.PINK};
	}
}