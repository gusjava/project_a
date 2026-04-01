package a.entity.gus06.sys.expression1.apply.op._xpr_sys1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}

	private Service engine;
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.expert1.engine");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof Map) return engine.t(new Object[]{value,opMap});
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
