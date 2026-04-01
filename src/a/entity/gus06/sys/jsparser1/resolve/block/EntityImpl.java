package a.entity.gus06.sys.jsparser1.resolve.block;

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
	
	public static final String TYPE_BLOCK = "block";
	public static final String TYPE_INSTRUCTION = "instruction";


	private Service cut;
	private Service removeComment;
	
	public EntityImpl() throws Exception
	{
		cut = Outside.service(this,"gus06.sys.jsparser1.resolve.block.cut");
		removeComment = Outside.service(this,"gus06.sys.jsparser1.tool.list.remove.comment");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = (List) o[0];
		R res = (R) o[1];
		
		if(input instanceof Map) return buildFromMap((Map) input, res);
		if(input instanceof List) return buildFromList((List) input, res);
		
		throw new Exception("Invalid input type: "+input.getClass().getName());
	}
	
	private Map buildFromMap(Map input, R res) throws Exception
	{
		if(!isGroup3(input)) throw new Exception("Invalid tag type: "+type(input));
		
		List value = (List) value(input);
		return buildFromList(value,res);
	}
	
	private Map buildFromList(List input, R res) throws Exception
	{
		input = (List) removeComment.t(input);
		
		List parts = (List) cut.t(input);
		List valueList = new ArrayList();
		for(int i=0;i<parts.size();i++)
		{
			List part = (List) parts.get(i);
			if(!part.isEmpty()) 
			{
				valueList.add(resolveInstruction(part, res));
			}
		}
		
		Map tag = new HashMap();
		tag.put(TYPE, TYPE_BLOCK);
		tag.put(VALUE, valueList);
		return tag;
	}
	
	
	private Map resolveInstruction(List part, R res) throws Exception
	{
		T t = (T) res.r("instruction");
		return (Map) t.t(new Object[]{part, res});
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