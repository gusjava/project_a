package a.entity.gus06.sys.statistics1.collector2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, G, E, P {

	public String creationDate() {return "20170602";}


	private Service builder;

	private Map collectors;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"factory#gus06.sys.statistics1.collector1");
		
		collectors = new HashMap();
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			P p = (P) get(key);
			p.p(value);
		}
	}
	
	
	private Object get(Object key) throws Exception
	{
		if(!collectors.containsKey(key))
			collectors.put(key,builder.g());
		return collectors.get(key);
	} 
	
	
	
	public void e() throws Exception
	{collectors.clear();}
	
	
	
	public Object g() throws Exception
	{return collectors;}
}
