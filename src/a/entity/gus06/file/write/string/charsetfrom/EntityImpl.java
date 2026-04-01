package a.entity.gus06.file.write.string.charsetfrom;

import a.framework.*;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180226";}
	


	private Service findCharset;

	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File f1 = (File) o[1];
		String text = (String) o[2];
		
		Charset charset = (Charset) findCharset.t(f1);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = new PrintStream(file,charset.name());
		p.print(text);
		p.close();
	}
}
