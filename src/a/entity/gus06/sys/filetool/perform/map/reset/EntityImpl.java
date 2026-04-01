package a.entity.gus06.sys.filetool.perform.map.reset;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220623";}
	
	public static final String KEY_ENTITY = "entity";

	
	public void p(Object obj) throws Exception
	{
		Map prop = (Map) obj;
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(!key.equals(KEY_ENTITY)) it.remove();
		}
	}
}
