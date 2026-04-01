package a.entity.gus06.sys.option.manager;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl extends S1 implements Entity, R, V, F, P, E {

	public String creationDate() {return "20141123";}


	private Service persister;
	private Map prop;


	public EntityImpl() throws Exception
	{
		persister = Outside.service(this,"gus06.app.persister1");
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	
	
	public void e() throws Exception
	{resetAll();}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Map) {change((Map) obj);return;}
		if(obj instanceof Set) {reset((Set) obj);return;}
		if(obj instanceof String) {reset((String) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object v = r((String) obj);
		return v!=null && v.equals("true");
	}
	
	
	public Object r(String key) throws Exception
	{
		String v = getOption(key);
		if(v!=null) return v;
		return defaultOption(key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{change(key,(String) obj);}
	
	
	private void modified()
	{send(this,"modified()");}
	
	
	
	
	
	private void setOption(String name, String value) throws Exception
	{persister.v("option_"+name,value);}
	
	private void resetOption(String name) throws Exception
	{persister.v("option_"+name,null);}
	
	private String getOption(String name) throws Exception
	{return (String) persister.r("option_"+name);}
	
	private String defaultOption(String name)
	{
		if(!prop.containsKey("option.default."+name)) return null;
		return (String) prop.get("option.default."+name);
	}
	
	
	
	private void change(Map map) throws Exception
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			setOption(key,value);
		}
		modified();
	}
	
	private void change(String name, String value) throws Exception
	{
		setOption(name,value);
		modified();
	}
	
	private void reset(Set set) throws Exception
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			resetOption(key);
		}
		modified();
	}
	
	private void reset(String name) throws Exception
	{
		resetOption(name);
		modified();
	}
	
	private void resetAll() throws Exception
	{
		Set set = (Set) persister.g();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("option_"))
			persister.v(key,null);
		}
		modified();
	}
}
