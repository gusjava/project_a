package a.entity.gus06.app.info.buildtime;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160808";}


	public static final String PROP_BUILDTIME = "jar.buildtime";
	
	
	
	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"props");
	}
	
	
	public Object g() throws Exception
	{
		return get(PROP_BUILDTIME);
	}
	
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return "?";
		return (String) prop.get(key);
	}
}
