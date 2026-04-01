package a.entity.gus06.sys.expression1.apply.op._ref_init;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160918";}


	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.cache1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new T1(obj);
	}
	
	
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data,""+obj);}
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
		{manager.v(key,data);}
	}
}
