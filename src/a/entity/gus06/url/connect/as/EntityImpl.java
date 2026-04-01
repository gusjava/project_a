package a.entity.gus06.url.connect.as;

import a.framework.*;
import java.net.URL;
import java.net.URLConnection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170202";}


	private Service rebuild;
	
	public EntityImpl() throws Exception
	{
		rebuild = Outside.service(this,"gus06.url.connect.rebuildurl");
	}	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		URL url = (URL) rebuild.t(o[0]);
		String agent = (String) o[1];
		
		URLConnection con = url.openConnection();
		con.addRequestProperty("User-Agent", agent);
		con.connect();
		return con;
	}
}
