package a.entity.gus06.sys.expression1.apply.op._write_inputstream;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180321";}
	


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.write.inputstream");
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
		public T1(File file){this.file = file;}
		
		public Object t(Object obj) throws Exception
		{return new E1(new Object[]{file,obj});}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data){this.data = data;}
		
		public void e() throws Exception
		{perform.p(data);}
	}
}
