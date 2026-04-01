package a.entity.gus06.sys.expression1.apply.op._call1;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170305";}
	
	public static final String FIELD = "output";


	private Service builder;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return call((Map) value,opMap);
		if(value instanceof List) return call((List) value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	
	private Object call(Map map, Map opMap) throws Exception
	{
		if(!map.containsKey(FIELD)) throw new Exception("Field not found: "+FIELD);
		Object value = map.get(FIELD);
		
		T t = (T) builder.t(new Object[]{value,opMap});
		return t.t(map);
	}
	
	
	private Object call(List list, Map opMap) throws Exception
	{
		if(list.isEmpty()) throw new Exception("Empty list, not callable");
		Object value = list.get(list.size()-1);
		
		T t = (T) builder.t(new Object[]{value,opMap});
		return t.t(list);
	}
}
