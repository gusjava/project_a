package a.entity.gus06.sys.expression1.apply.op._lower_c0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String[]) return array((String[]) obj);
		if(obj instanceof String) return lowerC0((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String[] array(String[] a)
	{
		String[] a1 = new String[a.length];
		for(int i=0;i<a1.length;i++) a1[i] = lowerC0(a[i]);
		return a1;
	}
	
	private String lowerC0(String s)
	{
		if(s.equals("")) return s;
		return s.substring(0,1).toLowerCase() + s.substring(1);
	}
}
