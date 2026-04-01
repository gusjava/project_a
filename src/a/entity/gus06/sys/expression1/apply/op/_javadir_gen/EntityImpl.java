package a.entity.gus06.sys.expression1.apply.op._javadir_gen;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160410";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.javagen");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return new T1((File) obj);
		if(obj instanceof File[]) return new T2((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private File dir;
		public T1(File dir) {this.dir = dir;}
		
		public Object t(Object obj) throws Exception
		{
			return new E1(dir,obj);
		}
	}
	
	private class E1 implements E
	{
		private File dir;
		private Object input;
		
		public E1(File dir, Object input)
		{
			this.dir = dir;
			this.input = input;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{input,dir});
		}
	}
	
	
	
	
	private class T2 implements T
	{
		private File[] dir;
		public T2(File[] dir) {this.dir = dir;}
		
		public Object t(Object obj) throws Exception
		{
			return new E2(dir,obj);
		}
	}
	
	private class E2 implements E
	{
		private File[] dir;
		private Object input;
		
		public E2(File[] dir, Object input)
		{
			this.dir = dir;
			this.input = input;
		}
		
		public void e() throws Exception
		{
			for(File d:dir)
			perform.p(new Object[]{input,d});
		}
	}
}
