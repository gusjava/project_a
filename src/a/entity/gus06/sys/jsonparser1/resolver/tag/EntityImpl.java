package a.entity.gus06.sys.jsonparser1.resolver.tag;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}


	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_INT = "int";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	
	
	private Service resolveGroup2;
	private Service resolveGroup3;
	
	public EntityImpl() throws Exception
	{
		resolveGroup2 = Outside.service(this,"gus06.sys.jsonparser1.resolver.tag.group2");
		resolveGroup3 = Outside.service(this,"gus06.sys.jsonparser1.resolver.tag.group3");
	}

	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = (Map) o[0];
		T t = (T) o[1];
		
		try{return resolveTag(tag,t);}
		catch(Exception e)
		{
			String message = "Failed to resolve tag:["+tag.get(VALUE)+"]";
			throw new Exception(message,e);
		}
	}
	
	
	private Object resolveTag(Map tag, T t) throws Exception
	{
		String type = type(tag);
		
		if(type.equals(TYPE_INT))		return resolveInt(tag);
		if(type.equals(TYPE_DOUBLE))		return resolveDouble(tag);
		if(type.equals(TYPE_STRING))		return resolveString(tag);
		if(type.equals(TYPE_ELEMENT))		return resolveElement(tag);
		if(type.equals(TYPE_GROUP2))		return resolveGroup2(tag,t);
		if(type.equals(TYPE_GROUP3))		return resolveGroup3(tag,t);
		
		throw new Exception("Unsupported tag type: "+type);
	}
	
	
	
	
	private Object resolveElement(Map tag) throws Exception
	{
		Object value = value(tag);
		
		if(value.equals("true")) return Boolean.TRUE;
		if(value.equals("false")) return Boolean.FALSE;
		if(value.equals("null")) return null;
		
		return value;
//		throw new Exception("Invalid identifier: "+value);
	}
	
	
	private Object resolveInt(Map tag)
	{return value(tag);}
	
	private Object resolveDouble(Map tag)
	{return value(tag);}
	
	private Object resolveString(Map tag)
	{return value(tag);}
	
	
	
	private Object resolveGroup2(Map tag, T t) throws Exception
	{
		List content = (List) value(tag);
		return resolveGroup2.t(new Object[]{content,t});
	}
	
	private Object resolveGroup3(Map tag, T t) throws Exception
	{
		List content = (List) value(tag);
		return resolveGroup3.t(new Object[]{content,t});
	}
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
}