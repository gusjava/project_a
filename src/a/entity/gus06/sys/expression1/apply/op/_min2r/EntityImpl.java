package a.entity.gus06.sys.expression1.apply.op._min2r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170820";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.data.perform.min2r");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Number) return new T1(obj);
		if(obj instanceof int[]) return new T1(obj);
		if(obj instanceof double[]) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{value,obj});}
	}
}
