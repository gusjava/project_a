package a.entity.gus06.map.puttoday;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170201";}


	private Service today;
	
	public EntityImpl() throws Exception
	{
		today = Outside.service(this,"gus06.time.today");
	}

		
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object value = o[1];
		
		map.put(today(),value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object value = o[1];
		
		Map map1 = new HashMap(map);
		map1.put(today(),value);
		
		return map1;
	}
	
	
	private String today() throws Exception
	{return (String) today.g();}
}
