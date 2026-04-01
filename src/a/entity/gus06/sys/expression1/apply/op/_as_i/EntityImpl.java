package a.entity.gus06.sys.expression1.apply.op._as_i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160630";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof I) return new I1((I) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class I1 implements I
	{
		private I i;
		public I1(I i){this.i = i;}
		
		public Object i() throws Exception
		{return i.i();}
	}
}