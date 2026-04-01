package a.entity.gus06.sys.expression1.apply.op._in;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160905";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.in");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		return new F1(obj);
	}
	
	
	private class F1 implements F
	{
		private Object data;
		public F1(Object data)
		{this.data = data;}
		
		public boolean f(Object obj) throws Exception
		{return perform.f(new Object[]{data,obj});}
	}
}
