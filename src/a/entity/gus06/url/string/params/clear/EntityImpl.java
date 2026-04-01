package a.entity.gus06.url.string.params.clear;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200421";}


	
	public Object t(Object obj) throws Exception
	{
		String url = urlToString(obj);
		String[] n = url.split("\\?",2);
		if(n.length==1) return url;
		
		return n[0];
	}
	
	
	private String urlToString(Object obj) throws Exception
	{
		if(obj instanceof URL) return ((URL) obj).toString();
		if(obj instanceof String) return (String) obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
