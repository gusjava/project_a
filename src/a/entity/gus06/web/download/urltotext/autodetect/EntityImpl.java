package a.entity.gus06.web.download.urltotext.autodetect;

import a.framework.*;
import java.net.URL;
import java.net.URLConnection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250201";}


	private Service findURL;
	private Service connect;
	private Service perform;
	private Service apacheHttp;

	public EntityImpl() throws Exception
	{
		findURL = Outside.service(this,"gus06.find.url");
		connect = Outside.service(this,"gus06.url.connect.as.mozilla50");
		perform = Outside.service(this,"gus06.web.download.urltotext.autodetect.perform");
		apacheHttp = Outside.service(this,"gus06.sys.apachehttp.m.get");
	}

	public Object t(Object obj) throws Exception
	{
		URL url = (URL) findURL.t(obj);
		if(url.getProtocol().equals("https")) return apacheHttp.t(url);
		
		URLConnection con = (URLConnection) connect.t(url);
		return (String) perform.t(con);
	}
}