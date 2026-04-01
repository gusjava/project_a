package a.entity.gus06.sys.parser3.cut.op.seq.sum;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";
	
	public static final String PLUS = "+";
	public static final String MINUS = "-";


	
	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		
		List kk = new ArrayList();
		List k = new ArrayList();
		Map minus = null;
		
		for(int i=0;i<l.size();i++)
		{
			Map m = (Map) l.get(i);
			
			if(isSymbol(m,PLUS))
			{
				if(!k.isEmpty())
				{
					kk.add(k);
					k = new ArrayList();
				}
			}
			else if(isSymbol(m,MINUS))
			{
				if(!k.isEmpty())
				{
					kk.add(k);
					k = new ArrayList();
				}
				minus = minus==null ? m : null;
			}
			else
			{
				if(k.isEmpty() && minus!=null)
				{
					k.add(minus);
					minus = null;
				}
				k.add(m);
			}
		}
		if(k.isEmpty()) return null;
		kk.add(k);
		
		if(kk.size()==1 && k.size()==l.size()) return null;
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
