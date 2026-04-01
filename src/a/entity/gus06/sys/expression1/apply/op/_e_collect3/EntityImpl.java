package a.entity.gus06.sys.expression1.apply.op._e_collect3;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170326";}


	private Service builderT;
	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.perform.collect3");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof List)		return new T1(value,opMap);
		
		if(value instanceof double[][])		return new T1(value,opMap);
		if(value instanceof float[][])		return new T1(value,opMap);
		if(value instanceof int[][])		return new T1(value,opMap);
		if(value instanceof long[][])		return new T1(value,opMap);
		if(value instanceof boolean[][])	return new T1(value,opMap);
		if(value instanceof Object[][])		return new T1(value,opMap);
		
		if(value instanceof double[])		return new T1(value,opMap);
		if(value instanceof float[])		return new T1(value,opMap);
		if(value instanceof int[])		return new T1(value,opMap);
		if(value instanceof long[])		return new T1(value,opMap);
		if(value instanceof boolean[])		return new T1(value,opMap);
		if(value instanceof Object[])		return new T1(value,opMap);
		
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
			if(obj instanceof P) return new E1(new Object[]{value,obj});
			
			T t = (T) builderT.t(new Object[]{obj,opMap});
			return new E1(new Object[]{value,t});
		}
	}
	
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o){this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
