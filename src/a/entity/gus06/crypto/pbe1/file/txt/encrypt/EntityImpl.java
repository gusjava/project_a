package a.entity.gus06.crypto.pbe1.file.txt.encrypt;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150625";}

	
	private Service encrypt;
	private Service read;

	public EntityImpl() throws Exception
	{
		encrypt = Outside.service(this,"gus06.crypto.pbe1.string.encrypt");
		read = Outside.service(this,"gus06.file.read.string");
	}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		String text = (String) read.t(file);
		
		text = (String) encrypt.t(text);
		
		PrintStream p = new PrintStream(file);
		p.print(text);
		p.close();
	}
}