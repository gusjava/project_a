package a.entity.gus06.sys.expression1.apply.op._upper_c0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String[]) return array((String[]) obj);
		if(obj instanceof String) return upperC0((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String[] array(String[] a)
	{
		String[] a1 = new String[a.length];
		for(int i=0;i<a1.length;i++) a1[i] = upperC0(a[i]);
		return a1;
	}
	
	private String upperC0(String s)
	{
		if(s.equals("")) return s;
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
}
