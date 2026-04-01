package a.entity.gus06.sys.expression1.apply.op._lower_c01;

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
		if(obj instanceof String) return lowerC01((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String[] array(String[] a)
	{
		String[] a1 = new String[a.length];
		for(int i=0;i<a1.length;i++) a1[i] = lowerC01(a[i]);
		return a1;
	}
	
	private String lowerC01(String s)
	{
		if(s.length()<3) return l(s);
		int n = s.length();
		return l(s.substring(0,1)) + s.substring(1,n-1) + l(s.substring(n-1));
	}
	
	private String l(String s)
	{return s.toLowerCase();}
}
