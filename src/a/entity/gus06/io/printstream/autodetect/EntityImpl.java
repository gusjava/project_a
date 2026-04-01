package a.entity.gus06.io.printstream.autodetect;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}


	private Service findCharset;
	
	public EntityImpl() throws Exception
	{findCharset = Outside.service(this,"gus06.file.string.info.charset");}
	


	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(!f.exists()) return new PrintStream(f);
		
		Charset c = (Charset) findCharset.t(f);
		if(c==null) return new PrintStream(f);
		return new PrintStream(f,c.name());
	}
}
