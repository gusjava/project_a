package a.entity.gus06.sys.jsonparser1.resolver.tag.group3;

import a.framework.*;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_SYMBOL = "symbol";
	
	private Service cutMethod1;
	private Service cutMethod2;

	public EntityImpl() throws Exception
	{
		cutMethod1 = Outside.service(this,"gus06.sys.parser3.cut.symbol.a1");
		cutMethod2 = Outside.service(this,"gus06.sys.parser3.cut.symbol.b1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		Map result = new HashMap();
		if(l.isEmpty()) return result;
		
		Map last = (Map) l.get(l.size()-1);
		if(isSymbol(last,",")) l.remove(l.size()-1);
		
		List cut = (List) cutMethod1.t(new Object[]{l,","});
		if(cut==null)
		{
			addToMap(result,l,t);
		}
		else
		{
			int number = cut.size();
			for(int i=0;i<number;i++)
			{
				List x = (List) cut.get(i);
				addToMap(result,x,t);
			}
		}
		return result;
	}
	
	
	
	private void addToMap(Map map, List l, T t) throws Exception
	{
		List cut = (List) cutMethod2.t(new Object[]{l,":"});
		if(cut==null) throw new Exception("Invalid element inside map");
		
		List keyPart = (List) cut.get(0);
		List valuePart = (List) cut.get(1);
		
		if(keyPart.size()==1)
		((Map) keyPart.get(0)).put(TYPE, TYPE_STRING);
		
		Object key = t.t(keyPart);
		Object value = t.t(valuePart);
		
		map.put(key,value);
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