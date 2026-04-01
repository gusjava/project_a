package a.entity.gus06.sys.apachehttp.build.request.post;

import a.framework.*;
import org.apache.http.client.methods.HttpPost;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}
	
	public static final String KEY_URL = "url";
	public static final String KEY_HEADER = "header";
	public static final String KEY_JSON = "json";


	private Service buildMap;
	private Service buildUrl;
	private Service setJson;
	private Service setHeader;

	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.sys.apachehttp.tool.request.build.inputmap");
		buildUrl = Outside.service(this,"gus06.sys.apachehttp.tool.request.build.urlstring");
		setJson = Outside.service(this,"gus06.sys.apachehttp.tool.request.setjson");
		setHeader = Outside.service(this,"gus06.sys.apachehttp.tool.request.setheader");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) buildMap.t(obj);
		
		String url = (String) buildUrl.t(get(map,KEY_URL));
		Map header = (Map) get(map,KEY_HEADER);
		Object json = get(map,KEY_JSON);
		
		HttpPost post = new HttpPost(url);
		
		setHeader.p(new Object[]{post,header});
		setJson.p(new Object[]{post,json});
		
		return post;
	}
	
	public Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
