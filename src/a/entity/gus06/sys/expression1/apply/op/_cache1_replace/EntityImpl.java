package a.entity.gus06.sys.expression1.apply.op._cache1_replace;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180307";}


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
		{
			String key = ""+obj;
			
			Object oldValue = manager.r(key);
			manager.v(key,data);
			
			return oldValue;
		}
	}
}
