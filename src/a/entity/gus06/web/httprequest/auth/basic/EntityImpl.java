package a.entity.gus06.web.httprequest.auth.basic;

import java.net.URLConnection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151127";}


	private Service byteToBase64;
	
	public EntityImpl() throws Exception
	{
		byteToBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		URLConnection con = (URLConnection) o[0];
		String login = (String) o[1];
		String password = (String) o[2];
		
		String info = login+":"+password;
		String encoding = (String) byteToBase64.t(info.getBytes());
		
		con.setRequestProperty("Authorization","Basic "+encoding);
	}
}
