package a.entity.gus06.sys.expression1.apply.op._iterate_lines_utf8;

import a.framework.*;
import java.io.File;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250517";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.file.string.reader.providelines.utf8");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return perform.t(obj);
		if(obj instanceof InputStream) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
