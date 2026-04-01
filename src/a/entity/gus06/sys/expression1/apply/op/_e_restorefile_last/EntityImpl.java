package a.entity.gus06.sys.expression1.apply.op._e_restorefile_last;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161018";}


	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.filebackup1.manager");
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
		{return new E1(file,(String) obj);}
	}
	
	private class E1 implements E
	{
		private File file;
		private String key;
		
		public E1(File file, String key)
		{
			this.file = file;
			this.key = key;
		}
		
		public void e() throws Exception
		{
			V v = (V) manager.r(key);
			v.v("last",file);
		}
	}
		
}
