package a.entity.gus06.app.prop.getlong;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20231027";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"prop");}
	
	
	public Object r(String key) throws Exception
	{return getProp(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) 
		{
			return getProp((String) obj);
		}
		if(obj instanceof Object[]) 
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			return getProp((String) o[0], (Long) o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Long getProp(String key) throws Exception
	{return getProp(key,null);}
	
	
	private Long getProp(String key, Long defaultValue) throws Exception
	{
		if(!prop.containsKey(key)) return defaultValue;
		String value = (String)prop.get(key);
		try{return Long.parseLong(value);}
		catch(NumberFormatException e)
		{throw new Exception("Invalid value format: "+value);}
	}
}