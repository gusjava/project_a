package a.entity.gus06.sys.jsparser1.resolve.function;

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
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	public static final String TYPE_FUNCTION = "function";
	
	public static final String FUNCTION_NAME = "name";
	public static final String FUNCTION_PARAMS = "params";
	public static final String FUNCTION_BLOCK = "block";
	

	private Service removeLF;
	private Service getParams;
	
	public EntityImpl() throws Exception
	{
		removeLF = Outside.service(this,"gus06.sys.jsparser1.tool.list.remove.lf");
		getParams = Outside.service(this,"gus06.sys.jsparser1.resolve.function.params");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		R res = (R) o[1];
		
		Map r1 = attempt1(input,res);
		if(r1!=null) return r1;
		
		Map r2 = attempt2(input,res);
		if(r2!=null) return r2;
		
		throw new Exception("Failed to resolve function: "+input);
	}
	
	
	private Map attempt1(List input, R res) throws Exception
	{
		//(m0)=>...
		
		if(input.size()<4) return null;
		
		Map m0 = mapAt(input,0);
		Map m1 = mapAt(input,1);
		Map m2 = mapAt(input,2);
		
		if(!isGroup1(m0)) return null;
		if(!isSymbol(m1,"=")) return null;
		if(!isSymbol(m2,">")) return null;
		
		List params = (List) getParams.t(value(m0));
		Map block = resolveBlock(truncate(input,3),res);
		
		Map valueMap = new HashMap();
		valueMap.put(FUNCTION_PARAMS,params);
		valueMap.put(FUNCTION_BLOCK,block);
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_FUNCTION);
		tag.put(VALUE, valueMap);
		return tag;
	}
	
	
	private Map attempt2(List input, R res) throws Exception
	{
		//m0(m1){m2}
		
		if(input.size()!=3) return null;
		
		Map m0 = mapAt(input,0);
		Map m1 = mapAt(input,1);
		Map m2 = mapAt(input,2);
		
		if(!isElement(m0)) return null;
		if(!isGroup1(m1)) return null;
		if(!isGroup3(m2)) return null;
		
		String name = (String) value(m0);
		List params = (List) getParams.t(value(m1));
		Map block = resolveBlock((List) value(m2),res);
		
		Map valueMap = new HashMap();
		valueMap.put(FUNCTION_NAME,name);
		valueMap.put(FUNCTION_PARAMS,params);
		valueMap.put(FUNCTION_BLOCK,block);
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_FUNCTION);
		tag.put(VALUE, valueMap);
		return tag;
	}
	
	
	
	
	private Map resolveBlock(List list, R res) throws Exception
	{
		T t = (T) res.r("block");
		return (Map) t.t(new Object[]{list, res});
	}
	
	
	
	private List truncate(List list, int limit)
	{
		List output = new ArrayList();
		for(int i=limit;i<list.size();i++) output.add(list.get(i));
		return output;
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
	
	private boolean isElement(Map m)
	{return hasType(m,TYPE_ELEMENT);}
	
	private boolean isGroup1(Map m)
	{return hasType(m,TYPE_GROUP1);}
	
	private boolean isGroup3(Map m)
	{return hasType(m,TYPE_GROUP3);}
}