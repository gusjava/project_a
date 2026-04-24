package a.entity.gus06.sys.apachehttp.m.post.full;

import a.framework.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import java.util.Map;
import java.util.Iterator;
import java.net.URL;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_HEADER = "header";
	public static final String KEY_ENTITY = "entity";


	private Service buildClient;
	private Service urlEncoding;

	public EntityImpl() throws Exception
	{
		buildClient = Outside.service(this,"gus06.sys.apachehttp.build.client");
		urlEncoding = Outside.service(this,"gus.x.tostring.map.urlencoding");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String url = toUrlStr(get(map,KEY_URL));
		Map header = (Map) get(map,KEY_HEADER);
		StringEntity sEntity = encodeEntity(map);
		
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
		
		if(sEntity!=null)
		{
			post.setEntity(sEntity);
		}
		
		try
		{
			HttpClient client = (HttpClient) buildClient.g();
			HttpResponse response = client.execute(post);
			
			Header[] headers = response.getAllHeaders();
			Map headerMap = new HashMap();
			for(Header h : headers)
			{
				String name = h.getName();
				String value = h.getValue();
				headerMap.put(name,value);
			}
			
			int code = response.getStatusLine().getStatusCode();
			
			HttpEntity httpEntity = response.getEntity();
			String body = EntityUtils.toString(httpEntity);
			
			Map m = new HashMap();
			m.put("out_code",""+code);
			m.put("out_body",body);
			m.put("out_header",headerMap);
			
			return m;
		}
		finally
		{
			post.releaseConnection();
		}
	}
	
	
	private StringEntity encodeEntity(Map map) throws Exception
	{
		Object data = get(map,KEY_ENTITY);
		if(data==null) return null;
		
		if(data instanceof String)
		{
			String entity = (String) data;
			StringEntity sEntity = new StringEntity(entity, ContentType.APPLICATION_JSON);
			sEntity.setContentEncoding("UTF-8");
			return sEntity;
		}
		if(data instanceof Map)
		{
			String entity = (String) urlEncoding.t(data);
			StringEntity sEntity = new StringEntity(entity, ContentType.APPLICATION_FORM_URLENCODED);
			sEntity.setContentEncoding("UTF-8");
			return sEntity;
		}
		throw new Exception("Unsupported body type: "+data.getClass().getName());
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
