package a.entity.gus06.convert.stringtoclass;

import a.framework.*;
import java.lang.reflect.Array;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170429";}


	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		int dim = 0;
		while(s.endsWith("[]"))
		{
			s = s.substring(0,s.length()-2);
			dim++;
		}
		
		Class c = classFor(s);
		if(dim==0) return c;
		
		Object array = Array.newInstance(c,new int[dim]);
		return array.getClass();
	}
	
	
	private Class classFor(String s) throws Exception
	{
		if(s.equals("int")) return Integer.TYPE;
		if(s.equals("double")) return Double.TYPE;
		if(s.equals("float")) return Float.TYPE;
		if(s.equals("long")) return Long.TYPE;
		if(s.equals("boolean")) return Boolean.TYPE;
		if(s.equals("byte")) return Byte.TYPE;
		if(s.equals("short")) return Short.TYPE;
		if(s.equals("char")) return Character.TYPE;
		
		return Class.forName(s);
	}
}
