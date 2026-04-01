package a.entity.gus06.app.prop.get;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140909";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"props");}
	
	
	public Object r(String key) throws Exception
	{return getProp(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) 
		{
			return getProp((String) obj);
		}
		if(obj instanceof String[]) 
		{
			String[] o = (String[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			return getProp(o[0],o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String getProp(String key)
	{return getProp(key,null);}
	
	private String getProp(String key, String defaultValue)
	{return (String) (prop.containsKey(key) ? prop.get(key) : defaultValue);}
}
