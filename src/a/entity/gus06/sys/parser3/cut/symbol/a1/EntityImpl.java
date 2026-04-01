package a.entity.gus06.sys.parser3.cut.symbol.a1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";
	

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		String symbol = (String) o[1];
		
		if(l.size()<3) return null;

		List kk = new ArrayList();
		List k = new ArrayList();
		
		for(int i=0;i<l.size();i++)
		{
			Map m = (Map) l.get(i);
			
			if(isSymbol(m,symbol))
			{
				if(k.isEmpty()) throw new Exception("Invalid cutting with symbol: "+symbol+" at position "+i);
				kk.add(k);
				k = new ArrayList();
			}
			else k.add(m);
		}
		if(!k.isEmpty()) kk.add(k);
		
		if(kk.size()==1) return null;
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