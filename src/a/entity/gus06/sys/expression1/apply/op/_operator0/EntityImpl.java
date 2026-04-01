package a.entity.gus06.sys.expression1.apply.op._operator0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof T) return new T0((T) value,opMap);
		
		if(!opMap.containsKey(value)) return null;
		
		T t = (T) opMap.get(value);
		return new T0(t,opMap);
	}
	
	
	private class T0 implements T
	{
		private T t;
		private Map opMap;
		
		public T0(T t, Map opMap)
		{
			this.t = t;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			return t.t(new Object[]{obj,opMap});
		}
	}
}
