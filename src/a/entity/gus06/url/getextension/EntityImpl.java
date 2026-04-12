package a.entity.gus06.url.getextension;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}

	
	public Object t(Object obj) throws Exception
	{
		URL url = toURL(obj);
		String name = url.getFile().split("\\?")[0];
		if(!name.contains(".")) return "";
		
		String[] n = name.split("\\.");
		return n[n.length-1];
	}
	
	private URL toURL(Object obj) throws Exception
	{
		if(obj instanceof URL) return (URL) obj;
		if(obj instanceof String) return new URL((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
