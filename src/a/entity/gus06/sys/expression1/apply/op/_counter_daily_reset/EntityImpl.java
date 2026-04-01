package a.entity.gus06.sys.expression1.apply.op._counter_daily_reset;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230908";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.persister1.counter.daily");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new E0(obj);
		if(obj instanceof Number) return new E0(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E0 implements E
	{
		private Object obj;
		public E0(Object obj) {this.obj = obj;}
	
		public void e() throws Exception
		{reset(obj);}
	}
	
	private void reset(Object obj) throws Exception
	{perform.v(""+obj, null);}
}