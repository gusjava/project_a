package a.entity.gus06.sys.expression1.apply.op._xpr_analyze;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}

	private Service readFile;
	private Service prepare;
	private Service analyze;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
		analyze = Outside.service(this,"gus06.sys.parser3.analyzer1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return analyze.t(prepare.t(readFile.t(obj)));
		if(obj instanceof String) return analyze.t(prepare.t(obj));
		if(obj instanceof List) return analyze.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
