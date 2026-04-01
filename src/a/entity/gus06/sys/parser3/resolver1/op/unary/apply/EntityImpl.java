package a.entity.gus06.sys.parser3.resolver1.op.unary.apply;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service prepare;

	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) throw new Exception("Invalid value type for operator: null");

		if(value instanceof String)
		return apply((String) value,t);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
	
	
	private Object apply(String value, T t) throws Exception
	{
		List list = (List) prepare.t(value);
		return t.t(list);
	}
}
