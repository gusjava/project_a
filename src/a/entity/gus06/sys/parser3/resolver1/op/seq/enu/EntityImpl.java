package a.entity.gus06.sys.parser3.resolver1.op.seq.enu;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151119";}

	public static final String VALUE = "value";
	public static final String TYPE = "type";
	
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_INT = "int";
	

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		int number = cut.size();
		if(number<2) throw new Exception("Invalid operation: ENU");
		
		Object key = null;
		List value = null;
		
		Map map = new HashMap();
		
		for(int i=0;i<number;i++)
		{
			List l = (List) cut.get(i);
			int number0 = l.size();
			
			if(i==0)
			{
				if(number0!=1) throw new Exception("Invalid operation: ENU");
				key = buildKey((Map) l.get(0),t);
			}
			else if(i<number-1)
			{
				if(number0<2) throw new Exception("Invalid operation: ENU");
				value = new ArrayList();
				for(int j=0;j<number0-1;j++) value.add(l.get(j));
				put(map,key,t.t(value));
				key = buildKey((Map) l.get(number0-1),t);
			}
			else
			{
				value = new ArrayList();
				for(int j=0;j<number0;j++) value.add(l.get(j));
				put(map,key,t.t(value));
			}
		}
		
		return map;
	}
	
	
	
	
	private Object buildKey(Map m, T t) throws Exception
	{
		if(m.get(TYPE).equals(TYPE_ELEMENT)) return ""+m.get(VALUE);
		if(m.get(TYPE).equals(TYPE_INT)) return ""+m.get(VALUE);
		if(m.get(TYPE).equals(TYPE_DOUBLE)) return ""+m.get(VALUE);
		if(m.get(TYPE).equals(TYPE_STRING)) return ""+m.get(VALUE);
		
		return t.t(m);
	}
	
	private void put(Map map, Object key, Object value)
	{
		if(key!=null && value!=null) map.put(key,value);
	}
}
