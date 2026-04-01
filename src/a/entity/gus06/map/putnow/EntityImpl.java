package a.entity.gus06.map.putnow;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170201";}


	private Service now;
	
	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
	}

		
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object value = o[1];
		
		map.put(now(),value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object value = o[1];
		
		Map map1 = new HashMap(map);
		map1.put(now(),value);
		
		return map1;
	}
	
	
	private String now() throws Exception
	{return (String) now.g();}
}
