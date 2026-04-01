package a.entity.gus06.java.srcfile.extract.root.checked;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}


	private Service readFile;
	private Service srcToClassPath;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		srcToClassPath = Outside.service(this,"gus06.java.srccode.extract.classpath");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File srcFile = (File) obj;
		
		try
		{
			String src = (String) readFile.t(srcFile);
			String classPath = (String) srcToClassPath.t(src);
			
			String[] nn = classPath.split("\\.");
			int len = nn.length;
			
			String fileName = srcFile.getName();
			if(!fileName.equals(nn[len-1]+".java"))
			throw new Exception("Invalid java file name: "+fileName+" (className="+nn[len-1]+")");
			
			File d = srcFile.getParentFile();
			for(int i=len-2;i>=0;i--)
			{
				if(!d.getName().equals(nn[i]))
				throw new Exception("Invalid java file location: "+d+" (srcFile="+srcFile+")");
				d = d.getParentFile();
			}
			return d;
		}
		catch(Exception e)
		{
			String message = "Failed to extract root for java file: "+srcFile;
			throw new Exception(message,e);
		}
	}
}
