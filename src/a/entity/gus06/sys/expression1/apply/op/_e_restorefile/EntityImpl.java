package a.entity.gus06.sys.expression1.apply.op._e_restorefile;

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
		{return new T2(file,(String) obj);}
	}
	
	private class T2 implements T
	{
		private File file;
		private String key1;
		
		public T2(File file, String key1)
		{
			this.file = file;
			this.key1 = key1;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(file,key1,(String) obj);}
	}
	
	private class E1 implements E
	{
		private File file;
		private String key1;
		private String key2;
		
		public E1(File file, String key1, String key2)
		{
			this.file = file;
			this.key1 = key1;
			this.key2 = key2;
		}
		
		public void e() throws Exception
		{
			V v = (V) manager.r(key1);
			v.v(key2,file);
		}
	}
		
}
