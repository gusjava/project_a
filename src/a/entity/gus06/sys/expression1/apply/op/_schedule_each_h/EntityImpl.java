package a.entity.gus06.sys.expression1.apply.op._schedule_each_h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160924";}
	
	public static final long FACTOR = 3600000;


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.schedule.each");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof E) return new T1((E) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private E e;
		public T1(E e) {this.e = e;}
		
		public Object t(Object obj) throws Exception
		{return new E1(e,toLong(obj));}
	}
	
	
	
	private class E1 implements E
	{
		private E e;
		private long t;
		
		public E1(E e, long t)
		{
			this.e = e;
			this.t = t*FACTOR;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{e,Long.valueOf(t)});}
	}
	
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
}
