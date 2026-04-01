package a.entity.gus06.sys.parser3.cut.symbol.a2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221013";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";
	

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		String symbol1 = (String) o[1];
		String symbol2 = (String) o[2];
		
		if(l.size()<4) return null;
	

		List kk = new ArrayList();
		List k = new ArrayList();
		
		int step = 0;
		for(int i=0;i<l.size();i++)
		{
			Map m = (Map) l.get(i);
			k.add(m);
			
			if(step==0)
			{
				if(isSymbol(m,symbol1)) step = 1;
			}
			else if(step==1)
			{
				if(isSymbol(m,symbol2))
				{
					k.remove(k.size()-1);
					k.remove(k.size()-1);
					
					if(k.isEmpty()) throw new Exception("Invalid cutting with symbols: "+symbol1+symbol2);
					kk.add(k);
					k = new ArrayList();
				}
				else step = 0;
			}
		}
		if(step>0 || k.isEmpty()) throw new Exception("Invalid cutting with symbols: "+symbol1+symbol2);
		kk.add(k);
		
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