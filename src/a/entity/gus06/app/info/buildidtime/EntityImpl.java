package a.entity.gus06.app.info.buildidtime;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190413";}


	public static final String PROP_BUILDID = "jar.buildid";
	public static final String PROP_BUILDTIME = "jar.buildtime";
	
	
	
	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object g() throws Exception
	{
		return get(PROP_BUILDID)+"|"+get(PROP_BUILDTIME);
	}
	
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return "?";
		return (String) prop.get(key);
	}
}
