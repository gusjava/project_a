package a.entity.gus06.java.fromclass.jarfile;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.net.URI;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140804";}


	private Service classToJarUrl;
	
	
	public EntityImpl() throws Exception
	{
		classToJarUrl = Outside.service(this,"gus06.java.fromclass.jarurl");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) classToJarUrl.t(obj);
		if(url!=null) return null;
		
		URI uri = url.toURI();
		if(uri==null) return null;
		
		return new File(uri.getPath());
	}
}
