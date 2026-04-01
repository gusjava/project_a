package a.entity.gus06.convert.maptourl;

import a.framework.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230220";}


	private Service mapToParams;

	public EntityImpl() throws Exception
	{
		mapToParams = Outside.service(this,"gus06.tostring.map.urlencoding");
		
	}

	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		StringBuffer b = new StringBuffer();
		
		String urlRoot = getString(map,"urlRoot",null);
		
		if(urlRoot!=null) b.append(urlRoot);
		else
		{
			String schema = getString(map,"schema","http");
			String host = getString(map,"host","localhost");
			String user = getString(map,"user",null);
			String port = getString(map,"port",null);
			
			b.append(schema);
			b.append("://");
			if(user!=null) b.append(user+"@");
			b.append(host);
			if(port!=null) b.append(":"+port);
		}
		
		String path = getString(map,"path",null);
		if(path!=null) b.append(path);
		
		Object params = get(map,"params");
		if(params!=null)
		{
			b.append("?");
			b.append(formatParams(params));
		}
		
		String anchor = getString(map,"anchor",null);
		if(anchor!=null)
		{
			b.append("#");
			b.append(anchor);
		}
		
		String s = b.toString();
		try{return new URL(s);}
		catch(MalformedURLException e){}
		
		return null;
	}
	
	private String getString(Map map, String key, String defaultValue)
	{
		if(map.containsKey(key)) return (String) map.get(key);
		return defaultValue;
	}
	
	private Object get(Map map, String key)
	{
		if(map.containsKey(key)) return map.get(key);
		return null;
	}
	
	private String formatParams(Object params) throws Exception
	{
		if(params instanceof String) return (String) params;
		if(params instanceof Map) return (String) mapToParams.t(params);
		throw new Exception("Invalid params type: "+params.getClass().getName());
	}
}
