package a.entity.gus06.sys.parser3.resolver1.op.seq.point;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20151031";}
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.isEmpty()) return null;
		
		Object base = t.t(cut.get(0));
		
		for(int i=1;i<cut.size();i++)
		{
			List l = (List) cut.get(i);
			base = combine(base,l,t);
		}
		return base;
	}
	
	private Object combine(Object base, List l, T t) throws Exception
	{
		if(l.size()!=1) throw new Exception("Invalid combine operation: (l.size=" + l.size() + ")");
		Map m = (Map) l.get(0);
		return combine(base,m,t);
	}
	
	private Object combine(Object base, Map m, T t) throws Exception
	{
		Object value = mapToValue(m,t);
		if(value==null) return null;
		
		T external = (T) ((G) t).g();
		if(external==null) return null;
		
		return external.t(new Object[]{base,value});
	}
	
	private Object mapToValue(Map m, T t) throws Exception
	{
		String type = (String) m.get(TYPE);
		boolean isGroup = type.startsWith("group");
		
		if(isGroup) return t.t(m);
		return m.get(VALUE).toString();
	}
}
