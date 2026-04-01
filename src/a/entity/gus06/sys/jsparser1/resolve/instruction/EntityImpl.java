package a.entity.gus06.sys.jsparser1.resolve.instruction;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221013";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_ELEMENT = "element";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	public static final String TYPE_INSTRUCTION = "instruction";
	public static final String TYPE_EXPORT_DEFAULT = "export_default";
	public static final String TYPE_IMPORT = "import";
	public static final String TYPE_CALL = "call";
	
	public static final String IMPORT_NAME = "name";
	public static final String IMPORT_LOCATION = "location";
	
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
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_INSTRUCTION);
		tag.put(VALUE, handle(input, res));
		return tag;
	}
	
	
	private Object handle(List list, R res) throws Exception
	{
		Map m0 = mapAt(list,0);
		if(isElement(m0,"export")) return handleExport(list,res);
		if(isElement(m0,"import")) return handleImport(list,res);
		if(isElement(m0,"if")) return handleIf(list,res);
		
		return handleUnsupported(list);
	}
	
	
	// EXPORT
	
	private Map handleExport(List list, R res) throws Exception
	{
		//export <m1>
		
		Map m1 = mapAt(list,1);
		if(isElement(m1,"default")) return handleExportDefault(list,res);
		
		return handleUnsupported(list);
	}
	
	private Map handleExportDefault(List list, R res) throws Exception
	{
		//export default <m2>
		
		Map m2 = mapAt(list,2);
		Map exp = resolveExpression(m2,res);
		
		if(exp==null) throw new Exception("No expression found for tag: "+m2);
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_EXPORT_DEFAULT);
		tag.put(VALUE, exp);
		return tag;
	}
	
	
	// IMPORT
	
	private Map handleImport(List list, R res) throws Exception
	{
		Map r1 = attemptImport1(list,res);
		if(r1!=null) return r1;
		
		Map r2 = attemptImport2(list,res);
		if(r2!=null) return r2;
		
		return handleUnsupported(list);
	}
	
	private Map attemptImport1(List list, R res) throws Exception
	{
		//import <m1> from <m3>
		
		if(list.size()!=4) return null;
		
		Map m1 = mapAt(list,1);
		Map m2 = mapAt(list,2);
		Map m3 = mapAt(list,3);
		
		if(!isElement(m1)) return null;
		if(!isElement(m2,"from")) return null;
		if(!isString(m3)) return null;
		
		String name = (String) value(m1);
		String location = (String) value(m3);
			
		Map valueMap = new HashMap();
		valueMap.put(IMPORT_NAME,name);
		valueMap.put(IMPORT_LOCATION,location);
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_IMPORT);
		tag.put(VALUE, valueMap);
		return tag;
	}
	
	private Map attemptImport2(List list, R res) throws Exception
	{
		//import(<m1>)
		
		if(list.size()!=2) return null;
		
		Map m1 = mapAt(list,1);
		if(!isGroup1(m1)) return null;
		
		return resolveCall(list,res);
	}
	
	
	// CALL
	
	private Map resolveCall(List list, R res) throws Exception
	{
		T t = (T) res.r("call");
		return (Map) t.t(new Object[]{list, res});
	}
	
	private Map resolveExpression(Map tag, R res) throws Exception
	{
		T t = (T) res.r("expression");
		return (Map) t.t(new Object[]{tag, res});
	}
	
	private Map resolveArray(List list, R res) throws Exception
	{
		T t = (T) res.r("array");
		return (Map) t.t(new Object[]{list, res});
	}
	
	
	// IF
	
	private Map handleIf(List list, R res) throws Exception
	{
		//if(<m1>) <m2>
		
		Map m1 = mapAt(list,1);
		if(!isGroup1(m1)) throw new Exception("Invalid if instruction: "+list);
		
		return handleUnsupported(list);
	}
	
	
	private Map handleUnsupported(Object value) throws Exception
	{
		Map unsupported = new HashMap();
		unsupported.put(TYPE,"unsupported_instr");
		unsupported.put(VALUE,value);
		
		return unsupported;
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