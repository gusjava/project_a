package a.entity.gus06.sys.expression1.apply.op._hasnot_add;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210103";}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return new F1((List) obj);
		if(obj instanceof Set) return new F1((Set) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class F1 implements F
	{
		private Collection c;
		public F1(Collection c) {this.c = c;}
		
		public boolean f(Object obj) throws Exception
		{return hasNotAdd(c,obj);}
	}
	
	
	private boolean hasNotAdd(Collection c, Object element)
	{
		if(c.contains(element)) return false;
		c.add(element);
		return true;
	}
}