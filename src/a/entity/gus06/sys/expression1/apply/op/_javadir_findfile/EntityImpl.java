package a.entity.gus06.sys.expression1.apply.op._javadir_findfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160410";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.java.srcdir.retrieve.javafile");
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
		private File dir;
		public T1(File dir) {this.dir = dir;}
		
		public Object t(Object obj) throws Exception
		{
			String classpath = (String) obj;
			return find.t(new Object[]{dir,classpath});
		}
	}
}
