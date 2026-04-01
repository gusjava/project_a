package a.entity.gus06.sys.expression1.apply.op._toprintstream;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160107";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return new PrintStream((File) obj);
		if(obj instanceof OutputStream) return new PrintStream((OutputStream) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
