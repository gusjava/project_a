package a.entity.gus06.app.cache;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, F, G, R, V, E, P {

	public String creationDate() {return "20170320";}


	private Map cache;

	public EntityImpl() throws Exception
	{cache = new HashMap();}
	
	
	public void e() throws Exception
	{cache.clear();}
	
	
	public Object g() throws Exception
	{return cache;}
	
	
	public void p(Object obj) throws Exception
	{cache = (Map) obj;}
	
	
	public boolean f(Object obj) throws Exception
	{return cache.containsKey(obj);}
	
	
	public Object r(String key) throws Exception
	{
		if(!cache.containsKey(key)) return null;
		return cache.get(key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		cache.put(key,obj);
	}
}
