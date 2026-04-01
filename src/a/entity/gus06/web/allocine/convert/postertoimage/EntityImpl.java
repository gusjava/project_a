package a.entity.gus06.web.allocine.convert.postertoimage;

import a.framework.*;
import java.util.Map;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191024";}
	
	public static final String KEY_POSTER = "poster";
	public static final String KEY_HREF = "href";


	private Service urlToImage;


	public EntityImpl() throws Exception
	{
		urlToImage = Outside.service(this,"gus06.convert.urltoimage");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(map==null) return null;
		if(!map.containsKey(KEY_POSTER)) return null;
		
		String href = (String) m(map,KEY_POSTER).get(KEY_HREF);
		URL url = new URL(href);
		return urlToImage.t(url);
	}
	
	private Map m(Map m, String key)
	{return (Map) m.get(key);}
}
