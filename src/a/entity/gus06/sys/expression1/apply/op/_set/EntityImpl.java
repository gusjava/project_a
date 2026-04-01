package a.entity.gus06.sys.expression1.apply.op._set;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250713";}


	private Service perform1;
	private Service perform2;
	
	public EntityImpl() throws Exception
	{
		perform1 = Outside.service(this,"gus06.reflection.setter.setvalue");
		perform2 = Outside.service(this,"gus06.reflection.setter.setvalues");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		return new T1(value);
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof String) return new T2(data,(String) obj);
			if(obj instanceof Map) return new E2(data,(Map) obj);
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	private class T2 implements T
	{
		private Object data;
		private String name;
		
		public T2(Object data, String name)
		{
			this.data = data;
			this.name = name;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,name,obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private Object name;
		private Object value;
		
		public E1(Object data, Object name, Object value)
		{
			this.data = data;
			this.name = name;
			this.value = value;
		}
		
		public void e() throws Exception
		{perform1.p(new Object[]{data,name,value});}
	}
	
	
	private class E2 implements E
	{
		private Object data;
		private Map map;
		
		public E2(Object data, Map map)
		{
			this.data = data;
			this.map = map;
		}
		
		public void e() throws Exception
		{perform2.p(new Object[]{data,map});}
	}
}