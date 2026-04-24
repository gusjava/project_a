package a.entity.gus06.web.httprequest.post.send;

import a.framework.*;
import java.util.Iterator;
import java.util.Map;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}

	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String KEY_CHARSET = "charset";
	public static final String KEY_BODY = "body";
	

	private Service prepare;
	private Service urlEncoding;
	
	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.web.httprequest.post.send.prepare");
		urlEncoding = Outside.service(this,"gus.x.tostring.map.urlencoding");
	}

	

	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		HttpURLConnection con = (HttpURLConnection) prepare.t(map);
		
		String in_charset = (String) get0(map,KEY_CHARSET,DEFAULT_CHARSET);
		String in_body = encodeBody(map);
		
		OutputStream os = con.getOutputStream();
		OutputStreamWriter wr = new OutputStreamWriter(os,in_charset);
		wr.write(in_body);
		wr.flush();
		
		String out_charset = findContentCharset(con);
	
		InputStream is = con.getInputStream();  
		InputStreamReader isr = new InputStreamReader(is,out_charset);
		BufferedReader rd = new BufferedReader(isr);
		
		String line;
		StringBuffer output = new StringBuffer();
		while((line = rd.readLine())!=null) output.append(line+"\n");
		
		wr.close();
		rd.close();
		
		return output.toString().trim();
	}

	
	
	
	private String findContentCharset(HttpURLConnection con)
	{
		String contentType = con.getContentType();
		if(contentType==null) return DEFAULT_CHARSET;
		contentType = contentType.replaceAll(" ","");
		if(!contentType.contains("charset=")) return DEFAULT_CHARSET;
		return contentType.split("charset=")[1];
	}
	
	private String encodeBody(Map map) throws Exception
	{
		Object body = get1(map,KEY_BODY);
		if(body instanceof String) return (String) body;
		if(body instanceof Map) return (String) urlEncoding.t(body);
		throw new Exception("Unsupported body type: "+body.getClass().getName());
	}
	
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		return get0(map,key,null);
	}
}
