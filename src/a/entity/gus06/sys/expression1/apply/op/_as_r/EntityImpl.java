package a.entity.gus06.sys.expression1.apply.op._as_r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof R) return new R1((R) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class R1 implements R
	{
		private R r;
		public R1(R r){this.r = r;}
		
		public Object r(String key) throws Exception
		{return r.r(key);}
	}
}