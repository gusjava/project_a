package a.entity.gus06.sys.expression1.apply.op._path;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof URL) return ((URL) obj).getPath();
		if(obj instanceof File) return ((File) obj).getAbsolutePath();
		if(obj instanceof Class) return ((Class) obj).getName();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
