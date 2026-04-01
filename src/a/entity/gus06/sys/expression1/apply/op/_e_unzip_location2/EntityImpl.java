package a.entity.gus06.sys.expression1.apply.op._e_unzip_location2;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231205";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.zip.perform.unzip.location2");
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
		{return new T2(f1,(File) obj);}
	}
	
	private class T2 implements T
	{
		private File f1;
		private File f2;
		
		public T2(File f1, File f2)
		{
			this.f1 = f1;
			this.f2 = f2;
		}
		
		public Object t(Object obj) throws Exception
		{return new T3(f1,f2,(String) obj);}
	}
	
	private class T3 implements T
	{
		private File f1;
		private File f2;
		private String location;
		
		public T3(File f1, File f2, String location)
		{
			this.f1 = f1;
			this.f2 = f2;
			this.location = location;
		}
		
		public Object t(Object obj) throws Exception
		{return new E1(f1,f2,location,(Map) obj);}
	}
	
	private class E1 implements E
	{
		private File f1;
		private File f2;
		private String location;
		private Map replMap;
		
		public E1(File f1, File f2, String location, Map replMap)
		{
			this.f1 = f1;
			this.f2 = f2;
			this.location = location;
			this.replMap = replMap;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{f1,f2,location,replMap});}
	}
}
