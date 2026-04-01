package a.entity.gus06.web.download.urltotext.autodetect.perform;

import a.framework.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.io.BufferedReader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250201";}

	public static final String DEFAULT_CHARSET = "ISO-8859-1"; //UTF-8


	public Object t(Object obj) throws Exception
	{
		URLConnection con = (URLConnection) obj;
		
		String contentType = con.getContentType();
		String encoding = contentTypeToEncoding(contentType);
		Charset charset = Charset.forName(encoding);
		
		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,charset);
		BufferedReader reader = new BufferedReader(isr);
		StringBuffer buffer = new StringBuffer();
		
		try
		{
			String line;
			while((line = reader.readLine())!=null)
			buffer.append(line).append("\n");
		}
		finally
		{
			reader.close();
			isr.close();
			if(con instanceof HttpURLConnection)
			((HttpURLConnection)con).disconnect();
		}
		return buffer.toString();
	}
	
	
	private String contentTypeToEncoding(String contentType)
	{
		if(contentType==null) return DEFAULT_CHARSET;
		String[] parts = contentType.split(";");
		for(String part : parts)
		{
			part = part.trim();
			if(part.startsWith("charset="))
			return part.substring(8).trim();
		}
		return DEFAULT_CHARSET;
	}
}
