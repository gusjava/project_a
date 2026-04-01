package a.entity.gus06.sys.expression1.apply.op._derivefile_name0;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231014";}

	
	private Service getExt;

	public EntityImpl() throws Exception
	{
		getExt = Outside.service(this,"gus06.file.getextension");
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
		private File value;
		public T1(File value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			String info = (String) obj;
			String ext = (String) getExt.t(value);
			File parent = value.getParentFile();
			String fileName = info+"."+ext;
			return new File(parent, fileName);
		}
	}
}