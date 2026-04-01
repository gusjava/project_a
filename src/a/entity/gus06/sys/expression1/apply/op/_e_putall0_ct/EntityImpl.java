package a.entity.gus06.sys.expression1.apply.op._e_putall0_ct;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161205";}


	private Service perform;
	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.putall.strict");
		buildMap = Outside.service(this,"gus06.map.build.map.value0");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof Map) return new T1(value);
		if(value instanceof File) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			Map m = (Map) buildMap.t(obj);
			return new E1(new Object[]{value,m});
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
