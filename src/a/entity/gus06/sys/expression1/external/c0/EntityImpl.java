package a.entity.gus06.sys.expression1.external.c0;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151031";}

	private Service findValue;
	private Service apply;

	public EntityImpl() throws Exception
	{
		findValue = Outside.service(this,"gus06.sys.expression1.external.findvalue");
		apply = Outside.service(this,"gus06.sys.expression1.apply");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map pool = (Map) o[0];
		Map opMap = (Map) o[1];
		
		return new External(pool,opMap);
	}
	
	
	
	
	private class External implements T
	{
		private Map pool;
		private Map opMap;
		
		public External(Map pool, Map opMap)
		{
			this.pool = pool;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof String)
			{
				String key = (String) obj;
				return findValue.t(new Object[]{pool,key});
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