package a.entity.gus06.sys.jsparser1.resolve.expression;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221013";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_INT = "int";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	public static final String TYPE_POINT = "point";


	private Service removeLF;
	private Service cut;
	
	public EntityImpl() throws Exception
	{
		removeLF = Outside.service(this,"gus06.sys.jsparser1.tool.list.remove.lf");
		cut = Outside.service(this,"gus06.sys.jsparser1.resolve.expression.cut");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		R res = (R) o[1];
		
		if(input instanceof Map) return handleTag((Map) input, res);
		if(input instanceof List) return handleList((List) input, res);
		
		throw new Exception("Unsupported Input type: "+input.getClass());
	}
	
	
	
	private Map handleTag(Map tag, R res) throws Exception
	{
		String type = (String) tag.get(TYPE);
		
		if(type.equals(TYPE_INT)) return tag;
		if(type.equals(TYPE_DOUBLE)) return tag;
		if(type.equals(TYPE_STRING)) return tag;
		if(type.equals(TYPE_ELEMENT)) return tag;
		
		if(type.equals(TYPE_GROUP2))
		{
			Object value = tag.get(VALUE);
			T t = (T) res.r("array");
			return (Map) t.t(new Object[]{value, res});
		}
		if(type.equals(TYPE_GROUP3))
		{
			Object value = tag.get(VALUE);
			T t = (T) res.r("struct");
			return (Map) t.t(new Object[]{value, res});
		}
		
		return handleUnsupported(tag);
	}
	
	
	
	private Map handleList(List list, R res) throws Exception
	{
		list = (List) removeLF.t(list);
		if(list.isEmpty()) return null;
		
		if(list.size()==1) return handleTag((Map) list.get(0), res);
		
		List parts = (List) cut.t(list);
		Map root = handleRoot((List) parts.get(0), res);
		if(parts.size()==1) return root;
		
		List valueList = new ArrayList();
		valueList.add(root);
		for(int i=1;i<parts.size();i++)
		{
			Map part = handlePart((List) parts.get(i), res);
			valueList.add(part);
		}
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_POINT);
		tag.put(VALUE, valueList);
		return tag;
	}
	
	
	private Map handleRoot(List list, R res) throws Exception
	{
		if(list.size()==1) return handleTag((Map) list.get(0), res);
		
		try{return resolveFunction(list, res);}
		catch(Exception e){}
		
		try{return resolveCall(list, res);}
		catch(Exception e){}
		
		return handleUnsupported(list);
	}
	
	
	private Map handlePart(List list, R res) throws Exception
	{
		if(list.size()==1) return handleTag((Map) list.get(0), res);
		
		try{return resolveCall(list, res);}
		catch(Exception e){}
		
		return handleUnsupported(list);
	}
	
	
	
	private Map resolveFunction(List list, R res) throws Exception
	{
		T t = (T) res.r("function");
		return (Map) t.t(new Object[]{list, res});
	}
	
	private Map resolveCall(List list, R res) throws Exception
	{
		T t = (T) res.r("call");
		return (Map) t.t(new Object[]{list, res});
	}
	
	
	
	
	private Map handleUnsupported(Object value) throws Exception
	{
		Map unsupported = new HashMap();
		unsupported.put(TYPE,"unsupported_exp");
		unsupported.put(VALUE,value);
		
		return unsupported;
	}
}