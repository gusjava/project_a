package a.entity.gus06.sys.expression1.apply.op._default1;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160220";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Wrap(obj);
	}
	
	
	private class Wrap implements T
	{
		private Object value;
		
		public Wrap(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return !isEmpty(value) ? value : obj;}
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
