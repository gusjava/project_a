package a.entity.gus06.file.write.string.autodetect.buildprintstream;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190420";}


	private Service findCharset;
	
	public EntityImpl() throws Exception
	{findCharset = Outside.service(this,"gus06.file.write.string.autodetect.findcharset");}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		
		if(!file.exists()) return new PrintStream(file);
		Charset c = (Charset) findCharset.t(obj);
		return new PrintStream(file,c.name());
	}
}
