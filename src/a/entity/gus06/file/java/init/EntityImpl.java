package a.entity.gus06.file.java.init;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20251219";}

	private Service generateEmptyClass;

	public EntityImpl() throws Exception
	{
		generateEmptyClass = Outside.service(this,"gus06.java.srccode.generate.emptyclass");
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		String javaSrc = (String) generateEmptyClass.t(file);
		
		PrintStream p = new PrintStream(file, "UTF-8");
		p.print(javaSrc);
		p.close();
	}
}
