package a.entity.gus06.sys.expression1.apply.op._e_movedown;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dirfile.perform.movedown");
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
		public T1(File f) {this.f = f;}
		
		public Object t(Object obj) throws Exception
		{return new E1(f,(String) obj);}
	}
	
	private class E1 implements E
	{
		private File f;
		private String sub;
		
		public E1(File f, String sub)
		{
			this.f = f;
			this.sub = sub;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{f,sub});}
	}
}
