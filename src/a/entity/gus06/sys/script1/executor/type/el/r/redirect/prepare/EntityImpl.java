package a.entity.gus06.sys.script1.executor.type.el.r.redirect.prepare;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160415";}


	private Service deepPut;
	private Service registerReturn;
	
	public EntityImpl() throws Exception
	{
		deepPut = Outside.service(this,"gus06.map.deep.put.replace");
		registerReturn = Outside.service(this,"gus06.sys.script1.tool.register.return1");
	}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		Map pool = (Map) o[1];
		Object value = o[2];
		
		if(value instanceof P) return value;
		if(value instanceof File) return value;
		if(value instanceof StringBuffer) return value;
		if(value instanceof StringBuilder) return value;
		if(value instanceof PrintStream) return value;
		if(value instanceof OutputStream) return value;
		
		if(value instanceof String) return new Wrap1(context,pool,(String) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class Wrap1 implements P, E
	{
		private Map context;
		private Map pool;
		private String key;
		private StringBuffer sb;
		
		public Wrap1(Map context, Map pool, String key)
		{
			this.context = context;
			this.pool = pool;
			this.key = key;
			sb = new StringBuffer();
		}
		
		public void p(Object obj) throws Exception
		{sb.append(obj);}
		
		public void e() throws Exception
		{
			String value = sb.toString();
			if(key.equals("return"))
				registerReturn.p(new Object[]{context,value});
			else deepPut.p(new Object[]{pool,key,value});
		}
	}
}
