package a.entity.gus06.sys.jsparser1.resolve.call;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221014";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	public static final String TYPE_CALL = "call";
	
	public static final String CALL_NAME = "name";
	public static final String CALL_ARGS = "args";

	
	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		R res = (R) o[1];
		
		return handleCall(input,res);
	}
	
	
	private Map handleCall(List list, R res) throws Exception
	{
		//<m0>(<m1>)
		
		if(list.size()!=2) throw new Exception("Failed to resolve call: "+list);
		
		Map m0 = mapAt(list,0);
		Map m1 = mapAt(list,1);
		
		if(!isElement(m0)) throw new Exception("Failed to resolve call: "+list);
		if(!isGroup1(m1)) throw new Exception("Failed to resolve call: "+list);
		
		String name = (String) value(m0);
		Map args = (Map) resolveArray((List) value(m1), res);
			
		Map valueMap = new HashMap();
		valueMap.put(CALL_NAME,name);
		valueMap.put(CALL_ARGS,args);
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_CALL);
		tag.put(VALUE, valueMap);
		return tag;
	}
	
	
	
	private Map resolveArray(List list, R res) throws Exception
	{
		T t = (T) res.r("array");
		return (Map) t.t(new Object[]{list, res});
	}
	
	
	
	
	
	private Map mapAt(List list, int index)
	{
		if(index>=list.size()) return null;
		return (Map) list.get(index);
	}
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
	
	private boolean hasValue(Map m, Object value)
	{return m!=null && value(m).equals(value);}
	
	private boolean hasType(Map m, String type)
	{return m!=null && type(m).equals(type);}
	
	private boolean isSymbol(Map m, String value)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,value);}
	
	private boolean isString(Map m)
	{return hasType(m,TYPE_STRING);}
	
	private boolean isElement(Map m)
	{return hasType(m,TYPE_ELEMENT);}
	
	private boolean isElement(Map m, String value)
	{return hasType(m,TYPE_ELEMENT) && hasValue(m,value);}
	
	private boolean isGroup1(Map m)
	{return hasType(m,TYPE_GROUP1);}
	
	private boolean isGroup3(Map m)
	{return hasType(m,TYPE_GROUP3);}
}