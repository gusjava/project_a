package a.entity.gus06.sys.expression1.apply.op._en_s;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160502";}


	private Service normalize;
	private Service readText;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.whitespace1");
		readText = Outside.service(this,"gus06.file.read.string.autodetect");
	}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return new F_false();
		if(obj instanceof String) return new F1((String) obj);
		if(obj instanceof File) return new F1((String) readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private String s;
		
		public F1(String s) throws Exception
		{this.s = normalize(s);}
		
		public boolean f(Object obj) throws Exception
		{
			String s0 = normalize(buildString(obj));
			return s.endsWith(s0);
		}
	}
	
	private class F_false implements F
	{
		public boolean f(Object obj) throws Exception {return false;}
	}
	
	private String buildString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
