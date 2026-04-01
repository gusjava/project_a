package a.entity.gus06.file.read.string.autodetect;

import java.io.File;
import java.nio.charset.Charset;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140710";}

	private Service findCharset;
	private Service read;
	
	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
		read = Outside.service(this,"gus06.file.read.string.autodetect.read");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file==null) return "";
		if(!file.exists()) return "";
		if(file.length()==0) return "";
		
		Charset charset = charset(file);
		return read.t(new Object[]{file,charset});
	}
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
}
