package a.entity.gus06.sys.base1.impl.write.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150803";}
	
	public static final String TYPE = "type";


	private Service writeJdbc;


	public EntityImpl() throws Exception
	{
		writeJdbc = Outside.service(this,"gus06.sys.base1.impl.write.map.jdbc");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		
		String type = (String) get(map,TYPE);
		if(type.equals("jdbc")) writeJdbc.p(obj);
		
		else throw new Exception("Unsupported base type: "+type);
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
