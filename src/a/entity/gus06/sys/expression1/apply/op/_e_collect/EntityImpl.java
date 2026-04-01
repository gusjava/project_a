package a.entity.gus06.sys.expression1.apply.op._e_collect;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	private Service builderT;
	private Service builderH;
	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		builderH = Outside.service(this,"gus06.sys.expression1.builder2.h");
		perform = Outside.service(this,"gus06.data.perform.collect");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof File) return new T1(value,opMap);
		if(value instanceof List) return new T1(value,opMap);
		if(value instanceof Set) return new T1(value,opMap);
		if(value instanceof Map) return new T1(value,opMap);
		if(value instanceof StringBuffer) return new T1(value,opMap);
		
		if(value instanceof double[][]) return new T2(value,opMap);
		if(value instanceof float[][]) return new T2(value,opMap);
		if(value instanceof int[][]) return new T2(value,opMap);
		if(value instanceof long[][]) return new T2(value,opMap);
		
		if(value instanceof boolean[][]) return new T1(value,opMap);
		if(value instanceof Object[][]) return new T1(value,opMap);
		
		if(value instanceof double[]) return new T2(value,opMap);
		if(value instanceof float[]) return new T2(value,opMap);
		if(value instanceof int[]) return new T2(value,opMap);
		if(value instanceof long[]) return new T2(value,opMap);
		
		if(value instanceof boolean[]) return new T1(value,opMap);
		if(value instanceof Object[]) return new T1(value,opMap);
		
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
	
	
	private class T2 implements T
	{
		private Object value;
		private Map opMap;
		
		public T2(Object value, Map opMap)
		{
			this.value = value;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			H h = (H) builderH.t(new Object[]{obj,opMap});
			return new E1(new Object[]{value,h});
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
