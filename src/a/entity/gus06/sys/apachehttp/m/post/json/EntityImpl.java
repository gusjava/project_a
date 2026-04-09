package a.entity.gus06.sys.apachehttp.m.post.json;

import a.framework.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import java.util.Map;
import java.util.Iterator;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171021";}
	
	public static final String CONTENT_TYPE = "application/json";
	
	public static final String KEY_URL = "url";
	public static final String KEY_HEADER = "header";
	public static final String KEY_JSON = "json";


	private Service buildClient;
	private Service generateJson;

	public EntityImpl() throws Exception
	{
		buildClient = Outside.service(this,"gus06.sys.apachehttp.build.client");
		generateJson = Outside.service(this,"gus.x.json.build1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String url = toUrlStr(get(map,KEY_URL));
		Map header = (Map) get(map,KEY_HEADER);
		String json = toJson(get(map,KEY_JSON));
		
		HttpClient client = (HttpClient) buildClient.g();
		
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", CONTENT_TYPE);
		
		if(json!=null)
		{
			StringEntity sEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
			sEntity.setContentEncoding("UTF-8");
			post.setEntity(sEntity);
		}
		if(header!=null)
		{
			Iterator it = header.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) header.get(key);
				post.addHeader(key,value);
			}
		}
		
		try
		{
			HttpResponse response = client.execute(post);
			HttpEntity httpEntity = response.getEntity();
			return EntityUtils.toString(httpEntity);
		}
		finally
		{
			post.releaseConnection();
		}
	}
	
	
	public Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private String toUrlStr(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Null url found");
		if(obj instanceof String) return (String) obj;
		if(obj instanceof URL) return ((URL) obj).toString();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String toJson(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Map) return (String) generateJson.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
