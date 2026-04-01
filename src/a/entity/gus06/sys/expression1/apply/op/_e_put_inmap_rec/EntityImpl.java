package a.entity.gus06.sys.expression1.apply.op._e_put_inmap_rec;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210131";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.put.inmap.rec");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof Map) return new T1(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{
			this.data = data;
		}
		
		public Object t(Object obj) throws Exception
		{return new T2(data,obj);}
	}
	
	private class T2 implements T
	{
		private Object data;
		private Object key;
		
		public T2(Object data, Object key)
		{
			this.data = data;
			this.key = key;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,key,obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private Object key;
		private Object value;
		
		public E1(Object data, Object key, Object value)
		{
			this.data = data;
			this.key = key;
			this.value = value;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{data,key,value});}
	}
}