package a.entity.gus06.sys.expression1.apply.op._e_truncate;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160129";}


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.truncate");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof List) return new T1(value);
		if(value instanceof Set) return new T1(value);
		if(value instanceof Map) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value){this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new E1(value,obj);}
	}
	
	
	private class E1 implements E
	{
		private Object value;
		private Object limit;
		
		public E1(Object value, Object limit)
		{
			this.value = value;
			this.limit = limit;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{value,limit});}
	}
}
