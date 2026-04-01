package a.entity.gus06.sys.expression1.apply.op._append0_ini;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160325";}
	


	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus06.file.write.properties.ini.merge.complete");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new Writer((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class Writer implements P
	{
		private File file;
		public Writer(File file) {this.file = file;}
		
		public void p(Object obj) throws Exception
		{writeFile.p(new Object[]{file,obj});}
	}
}
