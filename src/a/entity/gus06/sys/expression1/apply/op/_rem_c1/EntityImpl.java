package a.entity.gus06.sys.expression1.apply.op._rem_c1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160325";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return rem_c1((String) obj);
		if(obj instanceof List) return rem_c1((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String rem_c1(String s) throws Exception
	{
		if(s.length()<2) return "";
		return s.substring(0,s.length()-1);
	}
	
	private List rem_c1(List s) throws Exception
	{
		if(s.size()<2) return new ArrayList();
		List l = new ArrayList(s);
		l.remove(l.size()-1);
		return l;
	}
}
