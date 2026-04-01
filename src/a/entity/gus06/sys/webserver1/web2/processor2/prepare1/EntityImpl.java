package a.entity.gus06.sys.webserver1.web2.processor2.prepare1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140930";}

	public static final String KEY_INPUT_ADDRESS = "address";
	public static final String KEY_INPUT_URL = "url";
	public static final String KEY_INPUT_PATH = "path";
	public static final String KEY_INPUT_BODY = "body";
	
	public static final String KEY_DATA = "data";
	public static final String KEY_INPUT = "input";
	public static final String KEY_OUTPUT = "output";
	public static final String KEY_SESSION = "session";
	public static final String KEY_PARAMS_GET = "params_get";
	public static final String KEY_PARAMS_POST = "params_post";
	public static final String KEY_OUTPUT_HEADER = "output_header";
	



	private Service buildSession;
	private Service buildParamsGet;
	private Service buildParamsPost;


	public EntityImpl() throws Exception
	{
		buildSession = Outside.service(this,"gus06.sys.webserver1.web2.session");
		buildParamsGet = Outside.service(this,"gus06.sys.webserver1.web2.params.get");
		buildParamsPost = Outside.service(this,"gus06.sys.webserver1.web2.params.post");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		String address = (String) get(input,KEY_INPUT_ADDRESS);
		String url = (String) get(input,KEY_INPUT_URL);
		String body = (String) get(input,KEY_INPUT_BODY);
		String path = url.split("\\?",2)[0];
		input.put(KEY_INPUT_PATH,path);
		
		Map data = new HashMap();
		Map outputHeader = new HashMap();
		Map session = (Map) buildSession.r(address);
		Map paramsGet = (Map) buildParamsGet.t(url);
		Map paramsPost = (Map) buildParamsPost.t(body);
		StringBuffer buff = new StringBuffer();
		
		data.put("h",new OutputHolder(buff));
		
		
		Map main = new HashMap();
		
		main.put(KEY_DATA,data);
		main.put(KEY_INPUT,input);
		main.put(KEY_OUTPUT,buff);
		main.put(KEY_SESSION,session);
		main.put(KEY_PARAMS_GET,paramsGet);
		main.put(KEY_PARAMS_POST,paramsPost);
		main.put(KEY_OUTPUT_HEADER,outputHeader);
		
		return main;
	}
	
	
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	
	
	private class OutputHolder implements P
	{
		private StringBuffer b;
		public OutputHolder(StringBuffer b) {this.b = b;}
		
		public void p(Object obj)
		{
			String s = (String) obj;
			b.append(s);
			if(s.endsWith(">")) b.append("\n");
		}
	}
}
