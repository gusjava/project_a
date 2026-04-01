package a.entity.gus06.sys.expression1.apply.op._xpr_resolve;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200326";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.parser3.tool.resolve");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof Map) return perform.t(new Object[]{value,opMap});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
