package a.entity.gus06.web.download.urltotext.utf8.perform;

import a.framework.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170115";}
	
	private Charset charset = Charset.forName("UTF-8");


	public Object t(Object obj) throws Exception
	{
		URLConnection con = (URLConnection) obj;
		
		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,charset);
		StringBuffer buffer = new StringBuffer();
		
		try
		{
			int b;
			while((b = isr.read())!=-1)
			{buffer.append((char)b);}
		}
		finally
		{
			isr.close();
			if(con instanceof HttpURLConnection)
			((HttpURLConnection)con).disconnect();
		}
		return buffer.toString();
	}
}
