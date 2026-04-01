package a.entity.gus06.file.gusscript.init;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190723";}

	public static final String TEXT = "@code\n\n\n";
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		PrintStream p = new PrintStream(file);
		p.print(TEXT);
		p.close();
	}
}
