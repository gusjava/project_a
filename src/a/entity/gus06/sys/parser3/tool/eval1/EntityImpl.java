package a.entity.gus06.sys.parser3.tool.eval1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}

	private Service evaluate;
	private Service apply;
	
	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.parser3.evaluate");
		apply = Outside.service(this,"gus06.sys.expression1.apply");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object exp = o[0];
		Map opMap = (Map) o[1];
		Object var = o[2];
		
		T external = new External(opMap,var);
		return evaluate.t(new Object[]{external,exp});
	}
	
	
	
	private class External implements T
	{
		private Map opMap;
		private Object var;
		
		public External(Map opMap, Object var)
		{
			this.opMap = opMap;
			this.var = var;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof String)
			{
				return var;
			}
			if(obj instanceof Object[])
			{
				Object[] o = (Object[]) obj;
				if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
				
				Object value = o[0];
				Object op = o[1];
		
				return apply.t(new Object[]{opMap,value,op});
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
}
