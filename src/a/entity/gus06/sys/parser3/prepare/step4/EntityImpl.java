package a.entity.gus06.sys.parser3.prepare.step4;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151027";}
	
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_GROUP1 = "group1";
	public static final String TYPE_GROUP2 = "group2";
	public static final String TYPE_GROUP3 = "group3";
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		if(input==null) return null;
		
		List output = new ArrayList();
		
		while(!input.isEmpty())
		{
			Map m = buildNext(input);
			if(m!=null) output.add(m);
		}
		return output;
	}
	
	
	
	
	private Map buildNext(List input) throws Exception
	{
		Map m = (Map) input.remove(0);
		
		if(isSymbol(m,"(")) return buildGroup(input,TYPE_GROUP1,")");
		if(isSymbol(m,"[")) return buildGroup(input,TYPE_GROUP2,"]");
		if(isSymbol(m,"{")) return buildGroup(input,TYPE_GROUP3,"}");
		
		return m;
	}
	
	
	
	
	private Map buildGroup(List input, String type, String end) throws Exception
	{
		List content = new ArrayList();
		
		boolean endFound = false;
		while(!input.isEmpty() && !endFound)
		{
			Map m = buildNext(input);
			if(isSymbol(m,end)) endFound = true;
			else content.add(m);
		}
		
		Map group = new HashMap();
		group.put(TYPE,type);
		group.put(VALUE,content);
		return group;
	}
	
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
	
	private boolean hasValue(Map m, Object value)
	{return value(m).equals(value);}
	
	private boolean hasType(Map m, String type)
	{return type(m).equals(type);}
	
	
	
	private boolean isSymbol(Map m, String value)
	{return hasType(m,TYPE_SYMBOL) && hasValue(m,value);}
}