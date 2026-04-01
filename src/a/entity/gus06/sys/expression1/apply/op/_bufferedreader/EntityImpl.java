package a.entity.gus06.sys.expression1.apply.op._bufferedreader;

import a.framework.*;
import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180315";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof InputStreamReader)
			return fromInputStreamReader((InputStreamReader) obj);
		if(obj instanceof InputStream)
			return fromInputStream((InputStream) obj);
		if(obj instanceof Process)
			return fromProcess((Process) obj);
		if(obj instanceof Socket)
			return fromSocket((Socket) obj);
		if(obj instanceof File)
			return fromFile((File) obj);
		if(obj instanceof byte[])
			return fromByteArray((byte[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private BufferedReader fromInputStreamReader(InputStreamReader isr) throws Exception
	{
		return new BufferedReader(isr);
	}
	
	private BufferedReader fromInputStream(InputStream is) throws Exception
	{
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		return fromInputStreamReader(isr);
	}
	
	private BufferedReader fromProcess(Process p) throws Exception
	{
		InputStream is = p.getInputStream();
		return fromInputStream(is);
	}
	
	private BufferedReader fromSocket(Socket s) throws Exception
	{
		InputStream is = s.getInputStream();
		return fromInputStream(is);
	}
	
	private BufferedReader fromFile(File f) throws Exception
	{
		InputStream is = new FileInputStream(f);
		return fromInputStream(is);
	}
	
	private BufferedReader fromByteArray(byte[] ba) throws Exception
	{
		InputStream is = new ByteArrayInputStream(ba);
		return fromInputStream(is);
	}
}
