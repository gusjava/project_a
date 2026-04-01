package a.entity.gus06.sys.expression1.apply.op._xpr_eval0;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}

	private Service eval;
	
	public EntityImpl() throws Exception
	{
		eval = Outside.service(this,"gus06.sys.parser3.tool.eval0");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof String) return perform(value,opMap);
		if(value instanceof List) return perform(value,opMap);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object perform(Object exp, Map opMap) throws Exception
	{return eval.t(new Object[]{exp,opMap});}
}
