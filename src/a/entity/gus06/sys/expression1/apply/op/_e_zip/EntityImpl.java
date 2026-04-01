package a.entity.gus06.sys.expression1.apply.op._e_zip;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160817";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.zip.perform.zip");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return new T1(obj);
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof File[]) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object input;
		public T1(Object input) {this.input = input;}
		
		public Object t(Object obj) throws Exception
		{return new E1(input,(File) obj);}
	}
	
	private class E1 implements E
	{
		private Object input;
		private File zipFile;
		
		public E1(Object input, File zipFile)
		{
			this.input = input;
			this.zipFile = zipFile;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{input,zipFile});}
	}
}
