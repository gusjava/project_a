package a.entity.gus06.appli.entityaccess.api.sender;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150228";}

	public static final String KEY_WEBAPI_URL = "webapi_url";
	public static final String KEY_WEBAPI_USER = "webapi_user";
	public static final String KEY_WEBAPI_PWD = "webapi_pwd";
	
	public static final String KEY_ACTION = "action";
	public static final String KEY_USER = "user";
	public static final String KEY_PWD = "pwd";



	private Service optionManager;
	private Service httpRequest;
	private Service md5;

	public EntityImpl() throws Exception
	{
		optionManager = Outside.service(this,"gus06.sys.option.manager");
		httpRequest = Outside.service(this,"*gus06.web.httprequest.post.send");
		md5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = toMap(obj);
		
		String url = option(KEY_WEBAPI_URL);
		if(url==null) return "error:undefined url";
		
		String user = option(KEY_WEBAPI_USER);
		if(user!=null) input.put(KEY_USER,user);
		
		String pwd = option(KEY_WEBAPI_PWD);
		if(pwd!=null) input.put(KEY_PWD,pwd);

		computeMd5Fields(input);
		
		Map m = new HashMap();
		m.put("body",input);
		m.put("url",url);
		
		return httpRequest.t(m);
	}
	
	
	
	
	private void computeMd5Fields(Map input) throws Exception
	{
		Iterator it = input.keySet().iterator();
		
		Map input_ = new HashMap();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) input.get(key);
			input_.put(key+"_md5",md5.t(value));
		}
		
		input.putAll(input_);
	}
	
	
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof String)
		{
			Map map = new HashMap();
			map.put(KEY_ACTION,obj);
			return map;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private String option(String key) throws Exception
	{return (String) optionManager.r(key);}
}
