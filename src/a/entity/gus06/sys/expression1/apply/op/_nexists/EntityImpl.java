package a.entity.gus06.sys.expression1.apply.op._nexists;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160208";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(obj instanceof File)
		{
			File file = (File) obj;
			return Boolean.valueOf(!file.exists());
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
