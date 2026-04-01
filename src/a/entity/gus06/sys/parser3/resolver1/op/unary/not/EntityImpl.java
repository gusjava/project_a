package a.entity.gus06.sys.parser3.resolver1.op.unary.not;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service buildFilterInv;
	
	public EntityImpl() throws Exception
	{
		buildFilterInv = Outside.service(this,"gus06.feature.op.filter.inv");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) return null;

		if(value.equals(Boolean.TRUE)) return Boolean.FALSE;
		if(value.equals(Boolean.FALSE)) return Boolean.TRUE;
		
		if(value instanceof F) return buildFilterInv.t(value);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
}