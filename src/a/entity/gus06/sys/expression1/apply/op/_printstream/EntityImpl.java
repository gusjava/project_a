package a.entity.gus06.sys.expression1.apply.op._printstream;

import a.framework.*;
import java.net.Socket;
import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180315";}


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.printstream");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof PrintStream)	return obj;
		if(obj instanceof OutputStream)	return find.t(obj);
		if(obj instanceof File)		return find.t(obj);
		if(obj instanceof Process)	return find.t(obj);
		if(obj instanceof Socket)	return find.t(obj);
		if(obj instanceof JTextArea)	return find.t(obj);
		if(obj instanceof P)		return find.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
