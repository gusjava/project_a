package a.entity.gus06.file.write.string.autodetect.findcharset;

import a.framework.*;

import java.io.File;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160916";}
	
	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");


	private Service hasUnicode;
	private Service findCharset;
	private Service forceUTF8;


	public EntityImpl() throws Exception
	{
		hasUnicode = Outside.service(this,"gus06.filter.string.haschar.unicode");
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
		forceUTF8 = Outside.service(this,"gus06.file.write.string.autodetect.forceutf8");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String text = (String) o[1];
		
		return charset(file,text);
	}
	
	
	private Charset charset(File file, String text) throws Exception
	{
		if(!file.exists()) return Charset.defaultCharset();
		
		if(forceUTF8.f(file)) return CHARSET_UTF8;
		if(hasUnicode.f(text)) return CHARSET_UTF8;
		
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
}
