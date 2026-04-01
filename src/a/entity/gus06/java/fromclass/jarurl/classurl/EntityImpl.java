package a.entity.gus06.java.fromclass.jarurl.classurl;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.security.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140819";}


	private Service classToUrl;
	
	public EntityImpl() throws Exception
	{
		classToUrl = Outside.service(this,"gus06.java.fromclass.classurl");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Class c = (Class) obj;
	
		URL url = (URL) classToUrl.t(c);
		if(url==null) return null;
		
		return convertToJarUrl(url);
	}
	
	
	private URL convertToJarUrl(URL url) throws Exception
	{
		String url_ = url.toString();
		if(!url_.contains("!")) throw new Exception("Invalid class URL: "+url_);
		
		String[] n = url_.split("!");
		String lastPart = n[n.length-1];
		
		String s = url_.substring(4,url_.length()-lastPart.length()-1);
		return new URL(s);
	}

}
