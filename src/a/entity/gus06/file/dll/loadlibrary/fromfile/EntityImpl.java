package a.entity.gus06.file.dll.loadlibrary.fromfile;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150607";}

	private PrintStream out;

	public EntityImpl() throws Exception
	{
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	

	public void p(Object obj) throws Exception
	{
		File dllFile = (File) obj;
		if(!dllFile.isFile()) throw new Exception("Invalid DLL path: "+dllFile);
		
		String dllPath = dllFile.getAbsolutePath();
		System.load(dllPath);
		
		out.println("Library loaded: "+dllPath);
	}
}
