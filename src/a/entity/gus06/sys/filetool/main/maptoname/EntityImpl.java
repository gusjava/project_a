package a.entity.gus06.sys.filetool.main.maptoname;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141230";}
	
	public static final String KEY_ENTITY = "entity";
	

	private Service initName;
	

	public EntityImpl() throws Exception
	{
		initName = Outside.service(this,"gus06.sys.filetool.main.maptoname.init");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		if(!map.containsKey(KEY_ENTITY))
		map.put(KEY_ENTITY,initName.t(map));
		return map.get(KEY_ENTITY);
	}
}
