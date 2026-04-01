package a.entity.gus06.web.isbndb.api.perform;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150509";}

	public static final String FORMAT = "format";
	public static final String KEY = "key";
	public static final String TYPE = "type";
	public static final String INFO = "info";

	private Service urlToText;
	

	public EntityImpl() throws Exception
	{
		urlToText = Outside.service(this,"gus06.string.transform.url.urltotext");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String url = buildUrl(map);
		return urlToText.t(url);
	}
	
	
	
	private String buildUrl(Map map) throws Exception
	{
		String format = get(map,FORMAT);
		String key = get(map,KEY);
		String type = get(map,TYPE);
		String info = get(map,INFO);
		
		return "http://isbndb.com/api/v2/"+format+"/"+key+"/"+type+"/"+info;
	}
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}
