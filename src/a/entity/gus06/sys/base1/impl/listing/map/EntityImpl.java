package a.entity.gus06.sys.base1.impl.listing.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150803";}
	
	public static final String TYPE = "type";


	private Service listingJdbc;


	public EntityImpl() throws Exception
	{
		listingJdbc = Outside.service(this,"gus06.sys.base1.impl.listing.map.jdbc");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String type = (String) get(map,TYPE);
		if(type.equals("jdbc")) return listingJdbc.t(obj);
		
		throw new Exception("Unsupported base type: "+type);
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
