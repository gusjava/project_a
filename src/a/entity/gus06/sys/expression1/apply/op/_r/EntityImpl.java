package a.entity.gus06.sys.expression1.apply.op._r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160309";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof R) return new T1((R)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private R r;
		public T1(R r) {this.r = r;}
		
		public Object t(Object obj) throws Exception
		{return r.r((String) obj);}
	}
}
