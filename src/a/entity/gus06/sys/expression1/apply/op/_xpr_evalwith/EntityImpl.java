package a.entity.gus06.sys.expression1.apply.op._xpr_evalwith;

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
		eval = Outside.service(this,"gus06.sys.parser3.tool.evalwith");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof String) return new T1(value,opMap);
		if(value instanceof List) return new T1(value,opMap);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object exp;
		private Map opMap;
		
		public T1(Object exp, Map opMap)
		{
			this.exp = exp;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return eval.t(new Object[]{exp,opMap,obj});}
	}
}
