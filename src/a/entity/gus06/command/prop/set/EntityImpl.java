package a.entity.gus06.command.prop.set;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P, V, R {

	public String creationDate() {return "20140703";}

	private Map props;
	
	public EntityImpl() throws Exception
	{
		props = (Map) Outside.resource(this,"props");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] n = s.split("=",2);
		if(n.length!=2) throw new Exception("Invalid data: "+s);
		
		change(n[0],n[1]);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{change(key,(String) obj);}
	
	
	public Object r(String key) throws Exception
	{return props.containsKey(key)?props.get(key):null;}
	
	
	
	
	private void change(String key, String value) throws Exception
	{
		if(value==null) props.remove(key);
		else props.put(key,value);
	}
}
