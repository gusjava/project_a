package a.entity.gus06.web.youtube.api.getinfo;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180521";}

	public static final String URL1 = "http://www.youtube.com/get_video_info?video_id=";


	private Service urlToString;
	private Service parseToMap;
	
	
	public EntityImpl() throws Exception
	{
		urlToString = Outside.service(this,"gus06.web.download.urltotext.utf8");
		parseToMap = Outside.service(this,"gus06.map.string.stringtomap.builder3.urldecoding");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		
		String url = URL1+code;
		String page = (String) urlToString.t(url);
		return parseToMap.t(page);
	}
}
