package a.entity.gus06.sys.expression1.apply.op._bold;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190509";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Font) return ((Font) obj).deriveFont(Font.BOLD);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
