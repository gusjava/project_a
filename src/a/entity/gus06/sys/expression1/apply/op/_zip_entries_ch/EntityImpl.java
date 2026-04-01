package a.entity.gus06.sys.expression1.apply.op._zip_entries_ch;

import a.framework.*;
import java.io.File;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}


	private Service readFile;
	private Service findCharset;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.zip.findentries.withcharset");
		findCharset = Outside.service(this,"gus06.find.charset");
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
	
	
	
	public class T1 implements T
	{
		private File file;
		
		public T1(File file) {this.file = file;}
		
		public Object t(Object obj) throws Exception
		{
			Charset charset = (Charset) findCharset.t(obj);
			return readFile.t(new Object[]{file,charset});
		}
	}
}