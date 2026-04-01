package a.entity.gus06.sys.expression1.apply.op._operate;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180223";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		return new T1(value,opMap);
	}
	
	
	private class T1 implements T
	{
		private Object value;
		private Map opMap;
		
		public T1(Object value, Map opMap)
		{
			this.value = value;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			String opName = (String) obj;
			return new G1(value,opMap,opName);
		}
	}
	
	private class G1 implements G
	{
		private Object value;
		private Map opMap;
		private String opName;
		
		public G1(Object value, Map opMap, String opName)
		{
			this.value = value;
			this.opMap = opMap;
			this.opName = opName;
		}
		
		public Object g() throws Exception
		{
			if(opMap.containsKey(opName)) return build(opName);
			if(opMap.containsKey("_"+opName)) return build("_"+opName);
			
			throw new Exception("Unknown operator name: "+opName);
		}
		
		private Object build(String name) throws Exception
		{
			T op = (T) opMap.get(name);
			return op.t(new Object[]{value,opMap});
		}
	}
}
