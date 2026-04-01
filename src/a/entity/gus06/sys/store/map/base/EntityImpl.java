package a.entity.gus06.sys.store.map.base;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, T, R, F {

	public String creationDate() {return "20140929";}


	
	private Service inside;
	private Service outside;
	
	
	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"gus06.app.inside.store");
		outside = Outside.service(this,"gus06.sys.store.map.base.dir");
	}
	
	
	public Object r(String key) throws Exception
	{return map(key);}
	
	
	public Object t(Object obj) throws Exception
	{return map((String) obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return map((String) obj)!=null;}
	
	
	
	private Map map(String id) throws Exception
	{
		Map map = (Map) outside.r(id);
		if(map!=null) return map;
		return (Map) inside.r(id);
	}
}

