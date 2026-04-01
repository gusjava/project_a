package a.entity.gus06.sys.expression1.apply.op._backupfile_changed;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190724";}


	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.filebackup1.once.changed");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File) return new F1((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private File file;
		public F1(File file) {this.file = file;}
		
		public boolean f(Object obj) throws Exception
		{
			F f = (F) manager.r((String) obj);
			return f.f(file);
		}
	}
}
