package a.entity.gus06.sys.jsparser1.resolve.function.params;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221014";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_SYMBOL = "symbol";
	public static final String TYPE_ELEMENT = "element";
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		List params = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			Map m = (Map) list.get(i);
			if(i%2==0)
			{
				if(!isElement(m)) throw new Exception("Invalid param list: "+list);
				params.add(value(m));
			}
			else
			{
				if(!isSymbol(m,",")) throw new Exception("Invalid param list: "+list);
			}
		}
		return params;
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
	
	private boolean isElement(Map m)
	{return hasType(m,TYPE_ELEMENT);}
}