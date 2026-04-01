package a.entity.gus06.sys.expression1.apply.op._p_thr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180306";}


	private Service wrapPO;
	private Service wrapVKO;
	private Service startTh;
	
	public EntityImpl() throws Exception
	{
		wrapPO = Outside.service(this,"gus06.feature.wrap.po.e");
		wrapVKO = Outside.service(this,"gus06.feature.wrap.vstringobj.e");
		startTh = Outside.service(this,"gus06.thread.start");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof E) return new E1((E) obj);
		if(obj instanceof P) return new P1((P) obj);
		if(obj instanceof V) return new V1((V) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private E e;
		public E1(E e){this.e = e;}
		
		public void e() throws Exception
		{
			startTh.p(e);
		}
	}
	
	private class P1 implements P
	{
		private P p;
		public P1(P p){this.p = p;}
		
		public void p(Object obj) throws Exception
		{
			E e = (E) wrapPO.t(new Object[]{p,obj});
			startTh.p(e);
		}
	}
	
	private class V1 implements V
	{
		private V v;
		public V1(V v){this.v = v;}
		
		public void v(String key, Object obj) throws Exception
		{
			E e = (E) wrapVKO.t(new Object[]{v,key,obj});
			startTh.p(e);
		}
	}
}