package a.entity.gus06.sys.expression1.apply.op._e_copydir_re;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161109";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.op.copy.replace");
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
		private File f1;
		public T1(File f1) {this.f1 = f1;}
		
		public Object t(Object obj) throws Exception
		{return new E1(f1,(File) obj);}
	}
	
	private class E1 implements E
	{
		private File f1;
		private File f2;
		
		public E1(File f1, File f2)
		{
			this.f1 = f1;
			this.f2 = f2;
		}
		
		public void e() throws Exception
		{perform.p(new File[]{f1,f2});}
	}
}
