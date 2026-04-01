package a.entity.gus06.java.fromclass.jarurl;

import a.framework.*;
import java.net.URL;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140819";}


	private Service fromProtectionDomain;
	private Service fromClassUrl;
	
	
	public EntityImpl() throws Exception
	{
		fromProtectionDomain = Outside.service(this,"gus06.java.fromclass.jarurl.protectiondomain");
		fromClassUrl = Outside.service(this,"gus06.java.fromclass.jarurl.classurl");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		URL url1 = (URL) fromProtectionDomain.t(obj);
		if(url1!=null) return url1;
		
		URL url2 = (URL) fromClassUrl.t(obj);
		if(url2!=null) return url2;
		
		return null;
	}
}
