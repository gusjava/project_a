package a.entity.gus06.sys.expression1.apply.op._printstream_utf8;

import a.framework.*;
import java.net.Socket;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190703";}
	
	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.printstream.utf8");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Process)	return find.t(obj);
		if(obj instanceof Socket)	return find.t(obj);
		if(obj instanceof OutputStream)	return find.t(obj);
		if(obj instanceof File)		return find.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
