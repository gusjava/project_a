package a.entity.gus06.sys.expression1.apply.op._st;

import a.framework.*;
import java.net.URL;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}
	
	public static final boolean RESULT_FOR_NULL = false;

	private Service readText;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return new F1(null);
		if(obj instanceof String) return new F1((String) obj);
		if(obj instanceof Number) return new F1(""+obj);
		if(obj instanceof URL) return new F1(((URL) obj).toString());
		if(obj instanceof File) return new F1((String) readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private String s;
		public F1(String s) {this.s = s;}
		
		public boolean f(Object obj) throws Exception
		{
			if(s==null) return RESULT_FOR_NULL;
			String s0 = buildString(obj);
			return s.startsWith(s0);
		}
	}
	
	private String buildString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
