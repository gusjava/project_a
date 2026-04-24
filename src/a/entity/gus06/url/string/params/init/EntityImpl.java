package a.entity.gus06.url.string.params.init;

import a.framework.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200421";}


	private Service mapEncoding;

	public EntityImpl() throws Exception
	{
		mapEncoding = Outside.service(this,"gus.x.tostring.map.urlencoding");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return init(urlToString(o[0]),(Map) o[1]);
		if(o.length==3) return init(urlToString(o[0]),(String) o[1],o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private String urlToString(Object obj) throws Exception
	{
		if(obj instanceof URL) return ((URL) obj).toString();
		if(obj instanceof String) return (String) obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String init(String url, Map params) throws Exception
	{
		if(params.isEmpty()) return url;
		String[] n = url.split("\\?",2);
		
		String root = n[0];
		if(root.endsWith("/")) root = root.substring(0,root.length()-1);
		String part = (String) mapEncoding.t(params);
		
		return root+"?"+part;
	}
	
	
	private String init(String url, String key, Object value) throws Exception
	{
		Map params = new HashMap();
		params.put(key,value);
		return init(url,params);
	}
}
