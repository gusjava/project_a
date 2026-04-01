package a.entity.gus06.file.write.string.append0.autodetect;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160916";}
	
	private Service findCharset;
	private Service readFile;

	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.write.string.autodetect.findcharset");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect.read");
	}	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String text = (String) o[1];
		
		Charset charset = charset(file,text);
		String text0 = read(file,charset);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = new PrintStream(file,charset.name());
		p.print(text);
		p.print(text0);
		p.close();
	}
	
	
	private Charset charset(File file, String text) throws Exception
	{return (Charset) findCharset.t(new Object[]{file,text});}
	
	
	private String read(File file, Charset charset) throws Exception
	{return (String) readFile.t(new Object[]{file,charset});}
}
