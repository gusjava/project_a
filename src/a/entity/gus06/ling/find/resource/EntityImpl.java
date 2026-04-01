package a.entity.gus06.ling.find.resource;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;


public class EntityImpl implements Entity, R {

	public String creationDate() {return "20140808";}

	private Service insideLing;
	private Service langManager;
	
	private Map cache;

	public EntityImpl() throws Exception
	{
		insideLing = Outside.service(this,"gus06.app.inside.ling");
		langManager = Outside.service(this,"gus06.ling.language.manager");
		
		cache = new HashMap();
	}
	
	
	
	public Object r(String key) throws Exception
	{
		String lang = (String) langManager.g();
		String name = key+"_"+lang;
		
		if(cache.containsKey(name))
			return cache.get(name);
		
		Map resource = build(name);
		if(resource==null) return null;
		
		cache.put(name,resource);
		return resource;
	}
	
	
	private Map build(String name) throws Exception
	{return (Map) insideLing.t(name);}
}
