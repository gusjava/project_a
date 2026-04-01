package a.entity.gus06.sys.expression1.apply.op._trigger_onkey;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160915";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jna.keyboard.buffer.trigger.execute");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof E) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,(String) obj);}
	}
	
	
	
	private class E1 implements E
	{
		private Object data;
		private String key;
		
		public E1(Object data, String key)
		{
			this.data = data;
			this.key = key;
		}
		
		public void e() throws Exception
		{perform.v(key,data);}
	}
}
