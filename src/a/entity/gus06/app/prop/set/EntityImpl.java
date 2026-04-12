package a.entity.gus06.app.prop.set;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20251209";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"prop");}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(obj==null) prop.remove(key);
		else prop.put(key,""+obj);
	}
	
	public void p(Object obj) throws Exception
	{
		Map m = (Map) obj;
		prop.putAll(m);
	}
}
