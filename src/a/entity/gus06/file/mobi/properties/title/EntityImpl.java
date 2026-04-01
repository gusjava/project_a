package a.entity.gus06.file.mobi.properties.title;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191103";}
	
	public static final String KEY_TITLE = "TITLE";


	private Service toProp;

	public EntityImpl() throws Exception
	{
		toProp = Outside.service(this,"gus06.file.mobi.properties");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map prop = (Map) toProp.t(obj);
		return get(prop,KEY_TITLE);
	}
	
	
	private Object get(Map prop, String key)
	{
		if(!prop.containsKey(key)) return null;
		return prop.get(key);
	}
}
