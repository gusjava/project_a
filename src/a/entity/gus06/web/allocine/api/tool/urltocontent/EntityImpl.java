package a.entity.gus06.web.allocine.api.tool.urltocontent;

import a.framework.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.nio.charset.Charset;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}

	public static final Charset UTF8 = Charset.forName("utf8");


	private Service findUserAgent;

	public EntityImpl() throws Exception
	{
		findUserAgent = Outside.service(this,"gus06.web.allocine.api.tool.useragent");
	}

	

	public Object t(Object obj) throws Exception
	{
		String url = (String) obj;
		String userAgent = (String) findUserAgent.g();
		
		URLConnection con = new URL(url).openConnection();
		con.setRequestProperty("User-Agent",userAgent);
		con.connect();
		
		return getContent(con);
	}
	
	
	
	private String getContent(URLConnection con) throws IOException
	{
		StringBuffer buffer = new StringBuffer();
		
		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,UTF8);
		int b;
		while((b = isr.read())!=-1){buffer.append((char)b);}
		isr.close();
		
		if(con instanceof HttpURLConnection)
			((HttpURLConnection)con).disconnect();
		return buffer.toString();
	}
}
