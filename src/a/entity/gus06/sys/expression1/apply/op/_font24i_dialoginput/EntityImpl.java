package a.entity.gus06.sys.expression1.apply.op._font24i_dialoginput;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250506";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Font(Font.DIALOG_INPUT,Font.ITALIC,24);
	}
}