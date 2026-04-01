package a.entity.gus06.find.outputstream;

import a.framework.*;
import java.io.OutputStream;
import java.net.Socket;
import java.io.File;
import java.io.FileOutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180315";}



	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof OutputStream) return obj;
		
		if(obj instanceof Process) return ((Process) obj).getOutputStream();
		if(obj instanceof Socket) return ((Socket) obj).getOutputStream();
		if(obj instanceof File) return new FileOutputStream((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
