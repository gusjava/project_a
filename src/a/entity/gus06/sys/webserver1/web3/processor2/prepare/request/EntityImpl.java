package a.entity.gus06.sys.webserver1.web3.processor2.prepare.request;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160220";}
	
	
	public static final String KEY_URL = "url";
	public static final String KEY_PATH = "path";
	public static final String KEY_BODY = "body";
	public static final String KEY_PARAMS_GET = "params_get";
	public static final String KEY_PARAMS_POST = "params_post";
	
	
	private Service buildGet;
	private Service buildPost;


	public EntityImpl() throws Exception
	{
		buildGet = Outside.service(this,"gus06.sys.webserver1.web3.processor2.prepare.request.get");
		buildPost = Outside.service(this,"gus06.sys.webserver1.web3.processor2.prepare.request.post");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		String url = (String) get(input,KEY_URL);
		String body = (String) get(input,KEY_BODY);
		String path = url.split("\\?",2)[0];
		
		Map paramsGet = (Map) buildGet.t(url);
		Map paramsPost = (Map) buildPost.t(body);
		
		
		Map map = new HashMap();
		
		map.put(KEY_URL,url);
		map.put(KEY_BODY,body);
		map.put(KEY_PATH,path);
		map.put(KEY_PARAMS_GET,paramsGet);
		map.put(KEY_PARAMS_POST,paramsPost);
		
		return map;
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
