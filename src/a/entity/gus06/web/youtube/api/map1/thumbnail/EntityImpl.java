package a.entity.gus06.web.youtube.api.map1.thumbnail;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180521";}
	
	public static final String KEY = "thumbnail_url";
	
	private Service urlToImage;

	public EntityImpl() throws Exception
	{
		urlToImage = Outside.service(this,"gus06.convert.urltoimage");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String url = get(map,KEY);
		return urlToImage.t(url);
	}
	
	private String get(Map map, String key) throws Exception
	{
		if(map.containsKey(key)) return (String) map.get(key);
		throw new Exception("Unknown key: "+key);
	}
}
