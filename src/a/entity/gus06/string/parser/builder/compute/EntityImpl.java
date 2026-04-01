package a.entity.gus06.string.parser.builder.compute;

import a.framework.*;
import java.io.PushbackReader;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140818";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		PushbackReader pr = (PushbackReader) o[0];
		Map map = (Map) o[1];
		String key = (String) o[2];
		
		if(!map.containsKey(key))
			throw new Exception("Parsing rule not found: "+key);
		String rule = (String) map.get(key);
		
		
		
		return null;
	}
	
	
}
