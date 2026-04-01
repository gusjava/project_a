package a.entity.gus06.sys.expression1.apply.op._v;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160211";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof V) return new T1((V) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private V v;
		public T1(V v){this.v = v;}
		
		public Object t(Object obj) throws Exception
		{return new P1(v,(String) obj);}
	}
	
	
	private class P1 implements P
	{
		private V v;
		private String key;
		
		public P1(V v, String key)
		{
			this.v = v;
			this.key = key;
		}
		
		public void p(Object obj) throws Exception
		{v.v(key,obj);}
	}
}