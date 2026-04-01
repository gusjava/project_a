package a.entity.gus06.sys.store.obj.start;

import a.framework.*;
import java.util.Map;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140929";}
	
	public static final String KEY = "store.start";
	public static final String DEFAULTVALUE = "obj:start";

	private Service findObj;
	private Map prop;

	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object g() throws Exception
	{return findObj.t(rule());}
	
	
	
	private String rule()
	{
		if(prop.containsKey(KEY))
			return (String) prop.get(KEY);
		return DEFAULTVALUE;
	}
}
