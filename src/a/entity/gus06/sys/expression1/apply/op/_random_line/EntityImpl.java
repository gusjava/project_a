package a.entity.gus06.sys.expression1.apply.op._random_line;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190420";}


	private Service perform;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.line.keep.random");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(obj);
		if(obj instanceof File) return perform.t(readFile.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
