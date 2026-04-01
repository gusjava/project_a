package a.entity.gus06.sys.parser3.cut.op.ternary;

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
		List l = (List) obj;
		
		List l1 = new ArrayList();
		List l2 = new ArrayList();
		List l3 = new ArrayList();
		
		int number = l.size();
		int step = 0;
		
		for(int i=0;i<number;i++)
		{
			Map m = (Map) l.get(i);
			if(step==0)
			{
				if(isSymbol(m,"?")) step = 1;
				else l1.add(m);
			}
			else if(step==1)
			{
				if(isSymbol(m,":")) step = 2;
				else l2.add(m);
			}
			else l3.add(m);
		}
		
		if(step==2 && !l3.isEmpty()) return new List[]{l1,l2,l3};
		return null;
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
