package a.entity.gus06.file.mime.tika.detect.asstring;

import a.framework.*;
import org.apache.tika.Tika;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150811";}

	private Tika tika;

	public EntityImpl() throws Exception
	{tika = new Tika();}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return tika.detect((File) obj);
		if(obj instanceof InputStream) return tika.detect((InputStream) obj);
		if(obj instanceof URL) return tika.detect((URL) obj);
		if(obj instanceof byte[]) return tika.detect((byte[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
