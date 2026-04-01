package a.entity.gus06.ling.find.lingstring;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20140808";}


	private Service findResource;
	private Service notFound;

	public EntityImpl() throws Exception
	{
		findResource = Outside.service(this,"gus06.ling.find.resource");
		notFound = Outside.service(this,"gus06.ling.find.lingstring.notfound");
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(!isLingKey(key)) return key;
		try{return find(key);}
		catch(Exception e)
		{
			String message = "Ling key localization failed: "+key;
			throw new Exception(message,e);
		}
	}
	
	
	private boolean isLingKey(String key)
	{return !key.contains(" ") && key.equals(key.toLowerCase());}
	
	
	
	private String find(String key) throws Exception
	{
		String[] info = analyze(key);
		String name = info[0];
		String lingKey = info[1];
		
		Map map = (Map) findResource.r(name);
		if(map==null) return notFound(key);
		
		if(!map.containsKey(lingKey))
			return notFound(key);
		
		String value = (String) map.get(lingKey);
		if(value.equals("")) return "("+key+")";
		
		return value;
	}
	


	private String notFound(String key) throws Exception
	{
		notFound.p(key);
		return "["+key+"]";
	}
	
	
	private String[] analyze(String key)
	{
		if(!key.contains("_")) return new String[]{"default",key};
		return key.split("_",2);
	}
}
