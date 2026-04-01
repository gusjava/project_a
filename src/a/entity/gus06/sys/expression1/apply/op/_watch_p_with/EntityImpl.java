package a.entity.gus06.sys.expression1.apply.op._watch_p_with;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180405";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.support.watch.p.with");}
	
	
	
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
		private S support;
		
		public T1(S support)
		{this.support = support;}
		
		public Object t(Object obj) throws Exception
		{return new T2(support, (String) obj);}
	}
	
	
	private class T2 implements T
	{
		private S support;
		private String id;
		
		public T2(S support, String id)
		{
			this.support = support;
			this.id = id;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(support, id, (P) obj);}
	}
	
	
	private class E1 implements E
	{
		private S support;
		private String id;
		private P p;
		
		public E1(S support, String id, P p)
		{
			this.support = support;
			this.id = id;
			this.p = p;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{support,id,p});
		}
	}
}