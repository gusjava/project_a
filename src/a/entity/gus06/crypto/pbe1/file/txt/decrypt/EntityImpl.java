package a.entity.gus06.crypto.pbe1.file.txt.decrypt;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150625";}

	
	private Service decrypt;
	private Service read;

	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe1.string.decrypt");
		read = Outside.service(this,"gus06.file.read.string");
	}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		String text = (String) read.t(file);
		
		text = (String) decrypt.t(text);
		
		PrintStream p = new PrintStream(file);
		p.print(text);
		p.close();
	}
}