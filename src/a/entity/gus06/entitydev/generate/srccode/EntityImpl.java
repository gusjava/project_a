package a.entity.gus06.entitydev.generate.srccode;

import java.io.File;
import java.io.PrintStream;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140701";}

	
	private Service retrieveFile;
	private Service printCode;
	
	
	public EntityImpl() throws Exception
	{
		retrieveFile = Outside.service(this,"gus06.entitydev.retrieve.javafile");
		printCode = Outside.service(this,"gus06.entitydev.generate.srccode.printcode");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		File javaFile = (File) retrieveFile.t(map);
		File packageDir = javaFile.getParentFile();
		
		packageDir.mkdirs();
		if(!packageDir.isDirectory())
			throw new Exception("Could not create package dir: "+packageDir);
		
		PrintStream p = new PrintStream(javaFile);
		printCode.p(new Object[]{p,map});
		p.close();
	}
}
