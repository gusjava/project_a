package a.entity.gus06.sys.expression1.apply.op._take_last_ct;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170418";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return takeLast((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object takeLast(List l) throws Exception
	{
		if(l.isEmpty()) throw new Exception("No element found inside list");
		return l.remove(l.size()-1);
	}
}
