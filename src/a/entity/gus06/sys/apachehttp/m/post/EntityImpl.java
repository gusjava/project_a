package a.entity.gus06.sys.apachehttp.m.post;

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

	public String creationDate() {return "20171022";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_HEADER = "header";
	public static final String KEY_ENTITY = "entity";


	private Service buildClient;

	public EntityImpl() throws Exception
	{
		buildClient = Outside.service(this,"gus06.sys.apachehttp.build.client");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String url = toUrlStr(get(map,KEY_URL));
		Map header = (Map) get(map,KEY_HEADER);
		String entity = (String) get(map,KEY_ENTITY);
		
		HttpPost post = new HttpPost(url);
		
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
		
		if(entity!=null)
		{
			StringEntity sEntity = new StringEntity(entity, ContentType.APPLICATION_JSON);
			sEntity.setContentEncoding("UTF-8");
			post.setEntity(sEntity);
		}
		
		try
		{
			HttpClient client = (HttpClient) buildClient.g();
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
}
