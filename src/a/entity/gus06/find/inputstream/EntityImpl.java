package a.entity.gus06.find.inputstream;

import a.framework.*;
import java.io.InputStream;
import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140928";}


	private Service convertProperties;
	private Service convertString;
	private Service convertFile;
	private Service convertByteArray;
	
	
	public EntityImpl() throws Exception
	{
		convertProperties = Outside.service(this,"gus06.convert.propertiestoinputstream");
		convertString = Outside.service(this,"gus06.convert.stringtoinputstream.utf8");
		convertFile = Outside.service(this,"gus06.convert.filetoinputstream");
		convertByteArray = Outside.service(this,"gus06.convert.bytearraytoinputstream");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof InputStream) return obj;
		
		if(obj instanceof Process) return ((Process) obj).getInputStream();
		if(obj instanceof Socket) return ((Socket) obj).getInputStream();
		if(obj instanceof byte[]) return convertByteArray.t(obj);
		if(obj instanceof Properties) return convertProperties.t(obj);
		if(obj instanceof String) return convertString.t(obj);
		if(obj instanceof File) return convertFile.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}