package a.entity.gus06.sys.expression1.apply.op._font12i_serif;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Font(Font.SERIF,Font.ITALIC,12);
	}
}
