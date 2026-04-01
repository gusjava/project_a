package a.entity.gus06.sys.expression1.apply.op._e_complete2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180302";}


	private Service builderT;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.perform.complete2");
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
		private Object data;
		private Map opMap;
		
		public T1(Object data, Map opMap)
		{
			this.data = data;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			T t_key = (T) builderT.t(new Object[]{obj,opMap});
			return new T2(data,t_key,opMap);
		}
	}
	
	
	private class T2 implements T
	{
		private Object data;
		private T t_key;
		private Map opMap;
		
		public T2(Object data, T t_key, Map opMap)
		{
			this.data = data;
			this.t_key = t_key;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			T t_value = (T) builderT.t(new Object[]{obj,opMap});
			return new E1(new Object[]{data,t_key,t_value});
		}
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
