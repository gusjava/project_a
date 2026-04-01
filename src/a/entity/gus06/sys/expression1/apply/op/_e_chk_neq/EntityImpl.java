package a.entity.gus06.sys.expression1.apply.op._e_chk_neq;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}
	
	
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
		
		return new T1(obj);
	}
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return new E1(value,obj);}
	}
	
	
	private class E1 implements E
	{
		private Object obj1;
		private Object obj2;
		
		public E1(Object obj1, Object obj2)
		{
			this.obj1=obj1;
			this.obj2=obj2;
		}
		
		public void e() throws Exception
		{
			if(equals2(obj1,obj2)) throw new Exception("Objects are expected to be different: "+obj1+" & "+obj2);
		}
	}
	
	
	private boolean equals2(Object o1, Object o2) throws Exception
	{return compare.f(new Object[]{o1,o2});}
}
