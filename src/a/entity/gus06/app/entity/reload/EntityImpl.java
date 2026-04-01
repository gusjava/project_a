package a.entity.gus06.app.entity.reload;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140804";}

	private Service entityLoader;
	private Map jarMap;
	private Map classMap;

	public EntityImpl() throws Exception
	{
		entityLoader = Outside.service(this,"entityloader");
		jarMap = (Map) Outside.resource(this,"jarmap");
		classMap = (Map) Outside.resource(this,"classmap");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String entityName = (String) obj;
	
		jarMap.remove(entityName);
		classMap.remove(entityName);
		entityLoader.f(entityName);
	}
}
