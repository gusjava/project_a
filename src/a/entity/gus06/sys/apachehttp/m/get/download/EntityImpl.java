package a.entity.gus06.sys.apachehttp.m.get.download;

import a.framework.*;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import java.io.InputStream;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180323";}
	
	public static final String KEY_PUT = "put";


	private Service execute;
	private Service buildGet;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.apachehttp.execute.response");
		buildGet = Outside.service(this,"gus06.sys.apachehttp.build.request.get");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		P p = (P) get(map,KEY_PUT);
		
		HttpGet get = (HttpGet) buildGet.t(map);
		
		try
		{
			HttpResponse response = (HttpResponse) execute.t(get);
			
			long length = findContentLength(response);
			InputStream is = response.getEntity().getContent();
			p.p(new Object[]{is,Long.valueOf(length)});
		}
		finally
		{
			get.releaseConnection();
		}
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private long findContentLength(HttpResponse res)
	{
		Header[] h = res.getAllHeaders();
		for (int i = 0; i < h.length; i++)
		if(h[i].getName().toLowerCase().equals("content-length"))
			return Long.parseLong(h[i].getValue());
		return -1;
	}
}
