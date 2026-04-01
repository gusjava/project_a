package a.entity.gus06.file.read.string.cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221108";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File f = (File) o[0];
		Charset c = (Charset) o[1];
		
		if(f==null || !f.isFile()) return null;
		
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis,c.name());	
		char[] a= new char[(int)f.length()];
		isr.read(a,0,(int) f.length());
		isr.close();
		
		return new String(a);
	}
}