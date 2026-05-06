package a.entity.gus06.sys.expression1.apply.op._xpr_parse;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}

	private Service perform;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.parser3.prepare");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
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