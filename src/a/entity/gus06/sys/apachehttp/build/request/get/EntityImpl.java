package a.entity.gus06.sys.apachehttp.build.request.get;

import a.framework.*;
import org.apache.http.client.methods.HttpGet;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_HEADER = "header";


	private Service buildMap;
	private Service buildUrl;
	private Service setHeader;

	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.sys.apachehttp.tool.request.build.inputmap");
		buildUrl = Outside.service(this,"gus06.sys.apachehttp.tool.request.build.urlstring");
		setHeader = Outside.service(this,"gus06.sys.apachehttp.tool.request.setheader");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) buildMap.t(obj);
		
		String url = (String) buildUrl.t(get(map,KEY_URL));
		Map header = (Map) get(map,KEY_HEADER);
		
		HttpGet get = new HttpGet(url);
		
		setHeader.p(new Object[]{get,header});
		
		return get;
	}
	
	public Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
