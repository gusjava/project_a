package a.entity.gus06.sys.expression1.apply.op._haskey_remove;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return new F1((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class F1 implements F
	{
		private Map m;
		public F1(Map m) {this.m = m;}
		
		public boolean f(Object obj) throws Exception
		{return hasRemove(m,obj);}
	}
	
	
	private boolean hasRemove(Map m, Object element)
	{
		if(!m.containsKey(element)) return false;
		m.remove(element);
		return true;
	}
}
