package a.entity.gus06.find.printstream.utf8;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import java.net.Socket;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191108";}

	public static final String CHARSET = "UTF-8";

	private Service socketToPrintStream;
	private Service processToPrintStream;


	public EntityImpl() throws Exception
	{
		socketToPrintStream = Outside.service(this,"gus06.convert.sockettoprintstream.utf8");
		processToPrintStream = Outside.service(this,"gus06.convert.processtoprintstream.utf8");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof OutputStream) return new PrintStream((OutputStream) obj,false,CHARSET);
		if(obj instanceof File) return new PrintStream((File) obj,CHARSET);
		if(obj instanceof Socket) return socketToPrintStream.t(obj);
		if(obj instanceof Process) return processToPrintStream.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
