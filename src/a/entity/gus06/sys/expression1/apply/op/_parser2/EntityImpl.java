package a.entity.gus06.sys.expression1.apply.op._parser2;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170130";}


	private Service builder;
	private Service readText;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.parser2.engine1");
		readText = Outside.service(this,"gus06.file.read.string.autodetect");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	
	private class T1 implements T
	{
		private String delim;
		public T1(String delim) {this.delim = delim;}
		
		public Object t(Object obj) throws Exception
		{
			String data = getString(obj);
			T parser = (T) builder.t(delim);
			return parser.t(data);
		}
	}
	
	
	private String getString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) readText.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
