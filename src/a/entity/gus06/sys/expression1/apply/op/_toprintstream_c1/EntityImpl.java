package a.entity.gus06.sys.expression1.apply.op._toprintstream_c1;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}


	private Service buildPrintStream;
	
	public EntityImpl() throws Exception
	{buildPrintStream = Outside.service(this,"gus06.io.printstream.autodetect");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return buildPrintStream.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
