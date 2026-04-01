package a.entity.gus06.sys.expression1.apply.op._zip_entry_asstring;

import a.framework.*;
import java.io.File;
import java.util.zip.ZipFile;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231125";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.zip.readentry.asstring.autodetect");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof ZipFile) return new T1(obj);
		if(obj instanceof File) return new T1(obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private Object src;
		public T1(Object src) {this.src = src;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{src, obj});}
	}
}