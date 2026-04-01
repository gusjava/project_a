package a.entity.gus06.file.write.string.append.autodetect;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.io.FileOutputStream;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160916";}
	
	private Service findCharset;

	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.write.string.autodetect.findcharset");
	}	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String text = (String) o[1];
		Charset charset = charset(file,text);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file,true);
		PrintStream p = new PrintStream(fos,true,charset.name());
		p.print(text);
		p.close();
	}
	
	
	private Charset charset(File file, String text) throws Exception
	{return (Charset) findCharset.t(new Object[]{file,text});}
}
