package a.entity.gus06.sys.expression1.apply.op._write_video;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250825";}
	


	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus06.file.write.video.mp4.h264");
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
		{return new E1(file,obj);}
	}
	
	
	private class E1 implements E
	{
		private File file;
		private Object data;
		
		public E1(File file, Object data)
		{
			this.file = file;
			this.data = data;
		}
		
		public void e() throws Exception
		{writeFile.p(new Object[]{file,data});}
	}
}