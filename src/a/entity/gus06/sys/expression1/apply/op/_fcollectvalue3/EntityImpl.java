package a.entity.gus06.sys.expression1.apply.op._fcollectvalue3;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170605";}


	private Service builderTMap;
	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		builderTMap = Outside.service(this,"gus06.sys.expression1.builder2.t.map");
		perform = Outside.service(this,"gus06.map.value.fcollect3");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof Map) return new T1(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
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
			Map cMap = (Map) obj;
			cMap = (Map) builderTMap.t(new Object[]{cMap,opMap});
			return perform.t(new Object[]{value,cMap});
		}
	}
}
