package a.entity.gus06.sys.expression1.apply.op._eq;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	private Service compare;

	public EntityImpl() throws Exception
	{
		compare = Outside.service(this,"gus06.data.compare.o1");
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
		private Object value;
		public F1(Object value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{return equals2(value,obj);}
	}
	
	
	private boolean equals2(Object o1, Object o2) throws Exception
	{return compare.f(new Object[]{o1,o2});}
}
