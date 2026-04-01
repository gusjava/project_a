package a.entity.gus06.sys.expression1.apply.op._e_convert;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180307";}
	


	private Service handleFile;
	
	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.file.image.perform.convert.self2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong type number: "+o.length);
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
		{return new E1(file,(String) obj);}
	}
	
	
	private class E1 implements E
	{
		private File file;
		private String type;
		
		public E1(File file, String type)
		{
			this.file = file;
			this.type = type;
		}
		
		public void e() throws Exception
		{handleFile.p(new Object[]{file,type});}
	}
}
