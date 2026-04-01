package a.entity.gus06.web.api.provider;

import a.framework.*;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20191024";}


	private Service allocine_movie;
	private Service allocine_search;
	private Service youtube_getinfo;


	public EntityImpl() throws Exception
	{
		allocine_movie = Outside.service(this,"gus06.web.allocine.api.movie");
		allocine_search = Outside.service(this,"gus06.web.allocine.api.search");
		youtube_getinfo = Outside.service(this,"gus06.web.youtube.api.getinfo");
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("allocine_movie")) return allocine_movie;
		if(key.equals("allocine_search")) return allocine_search;
		if(key.equals("youtube_getinfo")) return youtube_getinfo;
		
		if(key.equals("keys")) return new String[]{"allocine_movie","allocine_search","youtube_getinfo"};
		
		throw new Exception("Unknown key: "+key);
	}
}
