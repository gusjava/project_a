package a.entity.gus06.sys.expression1.apply.op._extractor1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160424";}


	private Service readText;
	private Service extractor;

	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		extractor = Outside.service(this,"gus06.string.extract.extractor1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof File) return new T1((String) readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String data;
		public T1(String data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			String options = (String) obj;
			return new T2(data,options);		
		}
	}
	
	private class T2 implements T
	{
		private String data;
		private String options;
		
		public T2(String data, String options)
		{
			this.data = data;
			this.options = options;
		}
		
		public Object t(Object obj) throws Exception
		{
			String rule = (String) obj;
			return extract(data,rule,options);		
		}
	}
	
	private Object extract(String data, String rule, String options) throws Exception
	{return extractor.t(new String[]{data,rule,options});}
}
