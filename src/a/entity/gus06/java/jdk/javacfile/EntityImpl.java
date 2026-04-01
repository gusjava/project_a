package a.entity.gus06.java.jdk.javacfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20140725";}


	private Service jdkManager;

	public EntityImpl() throws Exception
	{
		jdkManager = Outside.service(this,"gus06.java.jdk.manager");
	}
	
	
	public Object g() throws Exception
	{
		File jdkDir = (File) jdkManager.g();
		return javacFile(jdkDir);
	}
	
	public Object t(Object obj) throws Exception
	{
		return javacFile((File) obj);
	}
	
	private File javacFile(File jdkDir) throws Exception
	{
		File javacExe = new File(new File(jdkDir,"bin"),"javac.exe");
		if(!javacExe.isFile()) throw new Exception("Javac exe not found: "+javacExe);
		return javacExe;
	}
}
