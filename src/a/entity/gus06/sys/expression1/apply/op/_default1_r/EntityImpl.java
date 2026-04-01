package a.entity.gus06.sys.expression1.apply.op._default1_r;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160227";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof R) return new Wrap((R) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class Wrap implements T
	{
		private R r;
		
		public Wrap(R r)
		{this.r = r;}
		
		public Object t(Object obj) throws Exception
		{return new R1(r,obj);}
	}
	
	
	private class R1 implements R
	{
		private R r;
		private Object d;
		
		public R1(R r, Object d)
		{
			this.r = r;
			this.d = d;
		}
		public Object r(String key) throws Exception
		{
			Object res = r.r(key);
			return !isEmpty(res) ? res : d;
		}
	}
	
	
	
	private boolean isEmpty(Object value)
	{
		if(value==null) return true;
		if(value.equals("")) return true;
		if(value instanceof Collection && ((Collection) value).isEmpty()) return true;
		if(value instanceof Map && ((Map) value).isEmpty()) return true;
		return false;
	}
}
