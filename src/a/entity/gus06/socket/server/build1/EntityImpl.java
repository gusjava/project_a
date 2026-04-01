package a.entity.gus06.socket.server.build1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20221110";}


	private Service build;
	private Service wrap;
	private Map cache;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.socket.server.build");
		wrap = Outside.service(this,"gus06.socket.server.wrap");
		cache = new HashMap();
	}
	
	public Object g() throws Exception
	{return cache;}
	

	public Object t(Object obj) throws Exception
	{
		int port = toInt(obj);
		String key = ""+port;
		
		if(!cache.containsKey(key))
		cache.put(key, wrap.t(build.t(port)));
		return holderAt(key);
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private Object holderAt(String key)
	{return cache.get(key);}
}