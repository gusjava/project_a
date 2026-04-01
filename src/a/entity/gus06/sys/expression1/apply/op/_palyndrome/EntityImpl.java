package a.entity.gus06.sys.expression1.apply.op._palyndrome;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		
		if(obj instanceof String) return Boolean.valueOf(isPalyndrome((String) obj));
		if(obj instanceof List) return Boolean.valueOf(isPalyndrome((List) obj));
		if(obj instanceof Object[]) return Boolean.valueOf(isPalyndrome((Object[]) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean isPalyndrome(String s)
	{
		StringBuffer b = new StringBuffer(s);
		return b.reverse().toString().equals(s);
	}
	
	
	private boolean isPalyndrome(List l)
	{
		int n = l.size()/2;
		for(int i=0;i<n;i++)
		{
			Object o1 = l.get(i);
			Object o2 = l.get(n-1-i);
			
			if(!o1.equals(o2)) return false;
		}
		return true;
	}
	
	
	private boolean isPalyndrome(Object[] o)
	{
		int n = o.length/2;
		for(int i=0;i<n;i++)
		{
			Object o1 = o[i];
			Object o2 = o[n-1-i];
			
			if(!o1.equals(o2)) return false;
		}
		return true;
	}
}
