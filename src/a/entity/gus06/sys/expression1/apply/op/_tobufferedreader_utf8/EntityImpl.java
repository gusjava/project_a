package a.entity.gus06.sys.expression1.apply.op._tobufferedreader_utf8;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.FileInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250321";}
	
	public static final String CHARSET = "UTF-8";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return fromFile((File) obj);
		if(obj instanceof Socket) return fromSocket((Socket) obj);
		if(obj instanceof InputStream) return fromInputStream((InputStream) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private BufferedReader fromFile(File file) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,CHARSET);
		return new BufferedReader(isr);
	}
	
	private BufferedReader fromSocket(Socket socket) throws Exception
	{
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,CHARSET);
		return new BufferedReader(isr);
	}
	
	private BufferedReader fromInputStream(InputStream is) throws Exception
	{
		InputStreamReader isr = new InputStreamReader(is,CHARSET);
		return new BufferedReader(isr);
	}
}