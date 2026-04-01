package a.entity.gus06.appli.allocinesearch.movie.formatdata;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150210";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map movie = (Map) obj;
		Map movie1 = new HashMap();
		
		if(movie.containsKey("link"))
		{
			List l = (List) movie.get("link");
			Map m = (Map) l.get(0);
			String href = (String) m.get("href");
			movie1.put("link",new URL(href));
		}
		
		if(movie.containsKey("poster"))
		{
			Map m = (Map) movie.get("poster");
			String href = (String) m.get("href");
			movie1.put("poster",new URL(href));
		}
		
		if(movie.containsKey("release"))
		{
			Map m = (Map) movie.get("release");
			String date = (String) m.get("releaseDate");
			movie1.put("release",date);
		}
		
		if(movie.containsKey("title"))
			movie1.put("title",movie.get("title"));	
		else movie1.put("title",movie.get("originalTitle"));
			
		if(movie.containsKey("productionYear"))
			movie1.put("productionYear",movie.get("productionYear"));
		
		if(movie.containsKey("castingShort"))
		{
			Map m = (Map) movie.get("castingShort");
			
			if(m.containsKey("actors"))
			movie1.put("actors",m.get("actors"));
			
			if(m.containsKey("directors"))
			movie1.put("directors",m.get("directors"));
		}
		
		if(movie.containsKey("statistics"))
		{
			Map m = (Map) movie.get("statistics");
			
			if(m.containsKey("userRating"))
			movie1.put("userRating",m.get("userRating"));
		}
		
		return movie1;
	}
}
