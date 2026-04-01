package a.entity.gus06.x.jdkdir.find.javac;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}
	
	public Object t(Object obj) throws Exception
	{
		File jdkDir = (File) obj;
		return javacFile(jdkDir);
	}

	private File javacFile(File jdkDir) throws Exception
	{
		File javacExe = new File(new File(jdkDir, "bin"), "javac.exe");
		if (!javacExe.isFile()) throw new Exception("Javac exe not found: " + javacExe);
		return javacExe;
	}
}