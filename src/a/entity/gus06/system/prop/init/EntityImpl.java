package a.entity.gus06.system.prop.init;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity {

	public String creationDate() {return "20141022";}


	private Map props;

	public EntityImpl() throws Exception
	{
		props = (Map) Outside.resource(this,"props");
		
		Iterator it = props.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("sysprop."))
			{
				String value = (String) props.get(key);
				System.setProperty(key.substring(8),value);
			}
		}
	}
}
