package a.entity.gus06.convert.urltostring;

import a.framework.*;
import java.net.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141008";}


	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		StringBuffer buff = new StringBuffer();
		URLConnection con = null;
		InputStream is = null;
		
		try
		{
			con = rebuildURL(url).openConnection();
			con.connect();

			is = con.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(is);
			
			int b;
			while((b = isr.read())!=-1)
			{buff.append((char)b);}
		}
		catch(Exception e)
		{
			String message = "url "+url+" failed to be retrieved as text";
			throw new Exception(message,e);
		}
		finally
		{
			if(is!=null) is.close();
			if(con!=null && con instanceof HttpURLConnection)
			((HttpURLConnection)con).disconnect();
		}
		return buff.toString();
	}
	
	
	
	private URL rebuildURL(URL url) throws Exception
	{
		String s = url.toString();
		return new URL(s.replace(" ","%20"));
	}
}
