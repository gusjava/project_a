package a.entity.gus06.sys.draw1.main;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}
	
	public static final String KEY_TYPE = "type";


	private Service findBuilder;

	public EntityImpl() throws Exception
	{
		findBuilder = Outside.service(this,"gus06.sys.draw1.findbuilder");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		if(!map.containsKey(KEY_TYPE)) throw new Exception("Key not found inside map: "+KEY_TYPE);
		String type = (String) map.get(KEY_TYPE);
		
		T builder = (T) findBuilder.t(type);
		return builder.t(map);
	}
	
	
}
