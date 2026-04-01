package a.entity.gus06.sys.expression1.apply.op._wrap_f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Boolean) return new F1((Boolean) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public class F1 implements F
	{
		private Boolean b;
		public F1(Boolean b){this.b = b;}
		
		public boolean f(Object obj) throws Exception
		{return b.booleanValue();}
	}
}