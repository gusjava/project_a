package a.entity.gus06.sys.expression1.apply.op._javafile_rename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.java.srcfile.rename.classname");
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
		private File f;

		public T1(File f)
		{this.f = f;}
		
		public Object t(Object obj) throws Exception
		{
			String newName = (String) obj;
			return perform.t(new Object[]{f,newName});
		}
	}
}
