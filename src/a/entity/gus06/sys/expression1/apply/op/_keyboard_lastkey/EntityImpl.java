package a.entity.gus06.sys.expression1.apply.op._keyboard_lastkey;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180226";}

	public static final String T = "constant";


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.jna.keyboard.buffer");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return perform.r("lastKey");
	}
}
