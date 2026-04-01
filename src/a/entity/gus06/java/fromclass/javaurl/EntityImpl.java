package a.entity.gus06.java.fromclass.javaurl;

import a.framework.*;
import java.net.URL;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140804";}


	private Service asResource;
	
	public EntityImpl() throws Exception
	{
		asResource = Outside.service(this,"gus06.java.fromclass.javaurl.resource");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url1 = (URL) asResource.t(obj);
		if(url1!=null) return url1;
		
		return null;
	}
}
