package a.entity.gus06.sys.expression1.apply.op._extend_t1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220530";}

	
	private Service perform;
	private Service builderT;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.feature.extend.t1");
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
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
			T t = (T) builderT.t(new Object[]{obj,opMap});
			return perform.t(new Object[]{value,t});
		}
	}
}