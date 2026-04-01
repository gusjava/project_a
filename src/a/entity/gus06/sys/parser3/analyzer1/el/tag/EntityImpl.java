package a.entity.gus06.sys.parser3.analyzer1.el.tag;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}


	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_INT = "int";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";



	private Service buildResult;
	
	public EntityImpl() throws Exception
	{
		buildResult = Outside.service(this,"gus06.sys.parser3.analyzer1.build.result");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = (Map) o[0];
		T t = (T) o[1];
		
		String type = type(tag);
		
		if(type.equals(TYPE_INT))		return analyzeInt(tag);
		if(type.equals(TYPE_DOUBLE))		return analyzeDouble(tag);
		if(type.equals(TYPE_STRING))		return analyzeString(tag);
		if(type.equals(TYPE_ELEMENT))		return analyzeElement(tag);
		if(type.equals(TYPE_GROUP1))		return analyzeGroup1(tag,t);
		if(type.equals(TYPE_GROUP2))		return analyzeGroup2(tag,t);
		if(type.equals(TYPE_GROUP3))		return analyzeGroup3(tag,t);
		
		throw new Exception("Unsupported tag type: "+type);
	}
	
	
	
	
	private Object analyzeElement(Map tag) throws Exception
	{
		Object value = value(tag);
		
		if(value.equals("true")) return result("boolean",Boolean.TRUE);
		if(value.equals("false")) return result("boolean",Boolean.FALSE);
		if(value.equals("null")) return result("null",null);
		
		return result(TYPE_ELEMENT,value);
	}
	
	
	private Object analyzeInt(Map tag) throws Exception
	{return result(TYPE_INT,value(tag));}
	
	private Object analyzeDouble(Map tag) throws Exception
	{return result(TYPE_DOUBLE,value(tag));}
	
	private Object analyzeString(Map tag) throws Exception
	{return result(TYPE_STRING,value(tag));}
	
	
	
	private Object analyzeGroup1(Map tag, T t) throws Exception
	{
		List content = (List) value(tag);
		return result(TYPE_GROUP1,t.t(content));
	}
	
	private Object analyzeGroup2(Map tag, T t) throws Exception
	{
		List content = (List) value(tag);
		return result(TYPE_GROUP2,content);
	}
	
	private Object analyzeGroup3(Map tag, T t) throws Exception
	{
		List content = (List) value(tag);
		return result(TYPE_GROUP3,content);
	}
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
	
	private Object result(String operator, Object content) throws Exception
	{return buildResult.t(new Object[]{operator,content});}
}
