package a.entity.gus06.sys.expression1.apply.op._launchjar_args;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.java.launchjar");
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
		private File jar;
		public T1(File jar) {this.jar = jar;}
		
		public Object t(Object obj) throws Exception
		{return new E1(jar,obj);}
	}
	
	private class E1 implements E
	{
		private File jar;
		private Object args;
		
		public E1(File jar, Object args)
		{
			this.jar = jar;
			this.args = args;
		}
		
		public void e() throws Exception
		{
			perform.p(new Object[]{jar,args});
		}
	}
}
