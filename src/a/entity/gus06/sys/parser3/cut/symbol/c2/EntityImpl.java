package a.entity.gus06.sys.parser3.cut.symbol.c2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231020";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";
	

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		String symbol = (String) o[1];
		
		if(l.size()<2) return null;
	
		Map m = (Map) l.get(l.size()-1);
		if(!isSymbol(m,symbol)) return null;
		
		List kk = new ArrayList();
		List k = new ArrayList();
		
		for(int i=0;i<l.size()-1;i++)
		k.add(l.get(i));
		
		kk.add(k);
		return kk;
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