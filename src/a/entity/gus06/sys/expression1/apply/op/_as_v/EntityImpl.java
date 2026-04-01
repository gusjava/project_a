package a.entity.gus06.sys.expression1.apply.op._as_v;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof V) return new V1((V) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class V1 implements V
	{
		private V v;
		public V1(V v){this.v = v;}
		
		public void v(String key, Object obj) throws Exception
		{v.v(key,obj);}
	}
}