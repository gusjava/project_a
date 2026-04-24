package a.entity.gus.y.find1.url;

import a.framework.*;
import java.net.URI;
import java.net.URL;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20151126";}

	private Service stringToUrl;
	private Service mapToUrl;
	
	public EntityImpl() throws Exception
	{
		stringToUrl = Outside.service(this,"gus.y.convert1.stringtourl");
		mapToUrl = Outside.service(this,"gus.y.convert1.maptourl");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof URL) return obj;
		if(obj instanceof URI) return ((URI) obj).toURL();
		if(obj instanceof File) return ((File) obj).toURI().toURL();
		if(obj instanceof String) return (URL) stringToUrl.t(obj);
		if(obj instanceof Map) return (URL) mapToUrl.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
