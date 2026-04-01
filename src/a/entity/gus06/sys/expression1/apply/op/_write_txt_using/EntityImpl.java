package a.entity.gus06.sys.expression1.apply.op._write_txt_using;

import a.framework.*;
import java.io.File;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180416";}
	


	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus06.file.write.string.cs");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new T1((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private File file;
		public T1(File file) {this.file = file;}
		
		public Object t(Object obj) throws Exception
		{return new T2(file,(Charset) obj);}
	}
	
	private class T2 implements T
	{
		private File file;
		private Charset charset;
		
		public T2(File file, Charset charset)
		{
			this.file = file;
			this.charset = charset;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(file,charset,obj);}
	}
	
	
	private class E1 implements E
	{
		private File file;
		private Charset charset;
		private String text;
		
		public E1(File file, Charset charset, Object data) throws Exception
		{
			this.file = file;
			this.charset = charset;
			this.text = toString_(data);
		}
		
		public void e() throws Exception
		{writeFile.p(new Object[]{file,charset,text});}
	}
	
	
	private String toString_(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null value");
		
		if(obj instanceof String) return (String) obj;
		if(obj instanceof StringBuffer) return obj.toString();
		if(obj instanceof Number) return obj.toString();
		if(obj instanceof Boolean) return obj.toString();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
