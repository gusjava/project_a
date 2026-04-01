package a.entity.gus06.sys.expression1.apply.op._printstream_n;

import a.framework.*;
import java.net.Socket;
import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201223";}


	private Service find;
	private Service printStreamToP2;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.printstream");
		printStreamToP2 = Outside.service(this,"gus06.convert.printstreamtop2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof PrintStream)	return printStreamToP2.t(obj);
		if(obj instanceof OutputStream)	return printStreamToP2.t(find.t(obj));
		if(obj instanceof File)		return printStreamToP2.t(find.t(obj));
		if(obj instanceof Process)	return printStreamToP2.t(find.t(obj));
		if(obj instanceof Socket)	return printStreamToP2.t(find.t(obj));
		if(obj instanceof JTextArea)	return printStreamToP2.t(find.t(obj));
		if(obj instanceof P)		return printStreamToP2.t(find.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
