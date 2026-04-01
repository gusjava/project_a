package a.entity.gus06.file.write.string.cs;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180416";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Charset charset = (Charset) o[1];
		String text = (String) o[2];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = new PrintStream(file,charset.name());
		p.print(text);
		p.close();
	}
}
