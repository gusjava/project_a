package a.entity.gus06.java.fromclass.docurl;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140821";}


	private String urlRoot;
	
	public EntityImpl() throws Exception
	{
		urlRoot = (String) Outside.resource(this,"string#file:///C:/javadoc/api/");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Class c = (Class) obj;
		String name = c.getName();
		
		String s = urlRoot + name.replace(".","/") + ".html";
		
		return new URL(s);
	}
}
