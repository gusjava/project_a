package a.entity.gus06.find.uri;

import a.framework.*;
import java.net.URI;
import java.net.URL;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140917";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof URI) return obj;
		if(obj instanceof URL) return ((URL) obj).toURI();
		if(obj instanceof File) return ((File) obj).toURI();
		if(obj instanceof String) return new URI((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
