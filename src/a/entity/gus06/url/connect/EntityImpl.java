package a.entity.gus06.url.connect;

import a.framework.*;
import java.net.URL;
import java.net.URLConnection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}


	private Service rebuild;
	
	public EntityImpl() throws Exception
	{
		rebuild = Outside.service(this,"gus06.url.connect.rebuildurl");
	}


	public Object t(Object obj) throws Exception
	{
		URL url = (URL) rebuild.t(obj);
		
		URLConnection con = url.openConnection();
		con.connect();
		return con;
	}
}
