package a.entity.gus06.sys.textfile1.read;

import java.io.File;
import java.nio.charset.Charset;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220208";}


	private Service isOfType;
	private Service findCharset;
	private Service read;
	
	public EntityImpl() throws Exception
	{
		isOfType = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
		read = Outside.service(this,"gus06.file.read.string.autodetect.read");
	}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file==null) return null;
		if(!file.exists()) return null;
		if(file.length()==0) return null;
		if(!isOfType.f(file)) return null;
		
		Charset charset = charset(file);
		return read.t(new Object[]{file,charset});
	}
	
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
}