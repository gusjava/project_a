package a.entity.gus06.web.httprequest.post.send.prepare;

import a.framework.*;
import java.util.Iterator;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191026";}

	public static final String METHOD_POST = "POST";
	
	public static final String KEY_URL = "url";
	public static final String KEY_HEADER = "header";
	public static final String KEY_AUTH = "auth";
	public static final String KEY_COOKIE = "cookie";
	public static final String KEY_METHOD = "method";
	public static final String KEY_FOLLOW_REDIRECT = "follow_redirect";
	
	

	private Service setAuth;
	private Service findURL;
	
	public EntityImpl() throws Exception
	{
		setAuth = Outside.service(this,"gus06.web.httprequest.auth.basic");
		findURL = Outside.service(this,"gus.y.find1.url");
	}

	



	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		URL url = (URL) findURL.t(get1(map,KEY_URL));
		String method = (String) get0(map,KEY_METHOD,METHOD_POST);
		
		Map header = (Map) get0(map,KEY_HEADER);
		String[] auth = (String[]) get0(map,KEY_AUTH);
		String cookie = (String) get0(map,KEY_COOKIE);
		Boolean followRedirect = (Boolean) get0(map,KEY_FOLLOW_REDIRECT);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(method);
		con.setDoOutput(true);
		
		if(header!=null)
		{
			Iterator it = header.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) header.get(key);
				con.setRequestProperty(key,value); 
			}
		}
		
		if(auth!=null) 
		setAuth.p(new Object[]{con,auth[0],auth[1]});
		
		if(cookie!=null) 
		con.setRequestProperty("Set-Cookie",cookie); 
		
		if(followRedirect!=null) 
		con.setInstanceFollowRedirects(followRedirect);
		
		return con;
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
