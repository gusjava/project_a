package a.entity.gus06.sys.expression1.apply.op._collectvalue_w;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161115";}


	private Service builderT;
	private Service builderF;
	private Service wrap_ft;
	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		builderF = Outside.service(this,"gus06.sys.expression1.builder2.f");
		wrap_ft = Outside.service(this,"gus06.feature.wrap.ft.t");
		perform = Outside.service(this,"gus06.data.perform.collectvalue");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return new T1t(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private class T1t implements T
	{
		private Object value;
		private Map opMap;
		
		public T1t(Object value, Map opMap)
		{
			this.value = value;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			T t = (T) builderT.t(new Object[]{obj,opMap});
			return new T2t(value,t,opMap);
		}
	}
	
	private class T2t implements T
	{
		private Object value;
		private T t;
		private Map opMap;
		
		public T2t(Object value, T t, Map opMap)
		{
			this.value = value;
			this.t = t;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			F f = (F) builderF.t(new Object[]{obj,opMap});
			T t1 = (T) wrap_ft.t(new Object[]{f,t});
			return perform.t(new Object[]{value,t1});
		}
	}
	
}
