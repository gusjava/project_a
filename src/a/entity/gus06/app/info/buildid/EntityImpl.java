package a.entity.gus06.app.info.buildid;

import java.util.Map;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170411";}


	public static final String PROP_BUILDID = "jar.buildid";
	
	
	
	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"props");
	}
	
	
	public Object g() throws Exception
	{
		return get(PROP_BUILDID);
	}
	
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return "?";
		return (String) prop.get(key);
	}
}
