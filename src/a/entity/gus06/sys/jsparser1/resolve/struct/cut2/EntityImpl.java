package a.entity.gus06.sys.jsparser1.resolve.struct.cut2;

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
		List l = (List) obj;
		List kk = new ArrayList();
		List k = new ArrayList();
		
		for(int i=0;i<l.size();i++)
		{
			Map m = (Map) l.get(i);
			if(isSymbol(m,":"))
			{
				if(k.isEmpty()) throw new Exception("Invalid entry");
				kk.add(k);
				k = new ArrayList();
			}
			else k.add(m);
		}
		kk.add(k);
		if(kk.size()>2) throw new Exception("Invalid entry");
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