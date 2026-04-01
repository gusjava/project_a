package a.entity.gus06.sys.expression1.apply.op._dir_smart_duplicate_wr;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190420";}
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.perform.smartreplace.duplicate.withroot");
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
		
		public T1(File dir)
		{this.dir = dir;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return dir;
			if(obj instanceof T) return new E1(dir,(T) obj);
			if(obj instanceof String) return new T2(dir,(String) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	
	private class T2 implements T
	{
		private File dir;
		private String s;
		
		public T2(File dir, String s)
		{
			this.dir = dir;
			this.s = s;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return dir;
			if(obj instanceof String) return new E2(dir,s,(String) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	
	private class E1 implements E
	{
		private File dir;
		private T t;
		
		public E1(File dir, T t)
		{
			this.dir = dir;
			this.t = t;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{dir,t});}
	}
	
	
	
	private class E2 implements E
	{
		private File dir;
		private String s1;
		private String s2;
		
		public E2(File dir, String s1, String s2)
		{
			this.dir = dir;
			this.s1 = s1;
			this.s2 = s2;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{dir,s1,s2});}
	}
}
