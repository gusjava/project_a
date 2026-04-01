package a.entity.gus06.sys.jsonparser1.resolver.tag2;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230131";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_INT = "int";
	public static final String TYPE_DOUBLE = "double";

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map tag1 = (Map) o[0];
		Map tag2 = (Map) o[1];
		T t = (T) o[2];
		
		if(value(tag1).equals("-")) return oppNumber(tag2);
		throw new Exception("Failed to resolve pear tags: "+value(tag1)+" & "+value(tag2));
	}
	
	
	private Object oppNumber(Map tag) throws Exception
	{
		String type = type(tag);
		
		if(type.equals(TYPE_INT))		return resolveInt(tag);
		if(type.equals(TYPE_DOUBLE))		return resolveDouble(tag);
		
		throw new Exception("Unsupported tag type: "+type);
	}
	
	
	private Object resolveInt(Map tag)
	{return ((Integer) value(tag)).intValue()*-1;}
	
	private Object resolveDouble(Map tag)
	{return ((Double) value(tag)).doubleValue()*-1;}
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
}