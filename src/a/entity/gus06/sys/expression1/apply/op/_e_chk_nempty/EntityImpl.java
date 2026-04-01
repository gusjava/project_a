package a.entity.gus06.sys.expression1.apply.op._e_chk_nempty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}
	
	
	private Service filter;
	
	public EntityImpl() throws Exception
	{
		filter = Outside.service(this,"gus06.data.filter.isempty");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new E1(obj);
	}
	
	
	private class E1 implements E
	{
		private Object obj;
		public E1(Object obj) {this.obj=obj;}
		
		public void e() throws Exception
		{
			if(filter.f(obj)) throw new Exception("Object is expected to be not empty: "+obj);
		}
	}
}
