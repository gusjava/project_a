package a.entity.gus06.sys.expression1.apply.op._charset_utf8;

import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200427";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Charset.forName("UTF-8");
	}
}
