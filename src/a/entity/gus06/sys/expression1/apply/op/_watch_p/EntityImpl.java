package a.entity.gus06.sys.expression1.apply.op._watch_p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180404";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.support.watch.p");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof S) return new T1((S) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private S s;
		
		public T1(S s)
		{this.s = s;}
		
		public Object t(Object obj) throws Exception
		{return new E1(s, (P) obj);}
	}
	
	
	
	
	private class E1 implements E
	{
		private S s;
		private P p;
		
		public E1(S s, P p)
		{
			this.s = s;
			this.p = p;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{s,p});
		}
	}
}
