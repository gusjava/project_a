package a.entity.gus06.java.srcfile.extract.classpath;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}


	private Service readFile;
	private Service srcToClassPath;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		srcToClassPath = Outside.service(this,"gus06.java.srccode.extract.classpath");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File srcFile = (File) obj;
		
		try
		{
			String src = (String) readFile.t(srcFile);
			String classPath = (String) srcToClassPath.t(src);
			return classPath;
		}
		catch(Exception e)
		{
			String message = "Failed to extract classpath for java file: "+srcFile;
			throw new Exception(message,e);
		}
	}
}
