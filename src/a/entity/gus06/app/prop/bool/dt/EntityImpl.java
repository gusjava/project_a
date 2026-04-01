package a.entity.gus06.app.prop.bool.dt;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140919";}


	public static final boolean DEFAULT_VALUE = true;

	private Map prop;
	
	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"prop");}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		String key = (String) obj;
		if(!prop.containsKey(key)) return DEFAULT_VALUE;
		
		String value = (String) prop.get(key);
		return value.toLowerCase().equals("true");
	}
}
