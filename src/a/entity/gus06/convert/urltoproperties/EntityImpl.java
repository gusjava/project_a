package a.entity.gus06.convert.urltoproperties;

import a.framework.*;
import java.net.*;
import java.io.*;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150625";}


	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) obj;
		Properties prop = new Properties();
		URLConnection con = null;
		InputStream is = null;
		
		try
		{
			con = rebuildURL(url).openConnection();
			con.connect();

			is = con.getInputStream(); 
			prop.load(is);
		}
		catch(Exception e)
		{
			String message = "url "+url+" failed to be retrieved as properties";
			throw new Exception(message,e);
		}
		finally
		{
			if(is!=null) is.close();
			if(con!=null && con instanceof HttpURLConnection)
			((HttpURLConnection)con).disconnect();
		}
		return prop;
	}
	
	
	
	private URL rebuildURL(URL url) throws Exception
	{
		String s = url.toString();
		return new URL(s.replace(" ","%20"));
	}
}
