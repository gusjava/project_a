package a.entity.gus06.sys.script1.access.tag.is.else1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160713";}

	public static final String K_NAME = "name";
	
	public static final String ELSE = "else";
	public static final String ELSEIF = "elseif";

	
	
	public boolean f(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		String name = (String) get(tag,K_NAME);
		if(name==null) return false;
		return name.equals(ELSE) || name.equals(ELSEIF);
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
