package a.entity.gus06.java.srcfile.extract.classdata.checked;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}
	
	public static final String KEY_CLASSNAME = "classname";
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_ROOTDIR = "rootdir";
	public static final String KEY_JAVAFILE = "javafile";


	private Service readFile;
	private Service srcToClassData;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		srcToClassData = Outside.service(this,"gus06.java.srccode.extract.classdata");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File srcFile = (File) obj;
		
		try
		{
			String src = (String) readFile.t(srcFile);
			Map classData = (Map) srcToClassData.t(src);
			
			String className = (String) classData.get(KEY_CLASSNAME);
			String package1 = (String) classData.get(KEY_PACKAGE);
			
			String[] nn = package1.split("\\.");
			int len = nn.length;
			
			String fileName = srcFile.getName();
			if(!fileName.equals(className+".java"))
			throw new Exception("Invalid java file name: "+fileName+" (className="+className+")");
			
			File d = srcFile.getParentFile();
			for(int i=len-1;i>=0;i--)
			{
				if(!d.getName().equals(nn[i]))
				throw new Exception("Invalid java file location: "+d+" (srcFile="+srcFile+")");
				d = d.getParentFile();
			}
			
			classData.put(KEY_ROOTDIR,d);
			classData.put(KEY_JAVAFILE,srcFile);
			
			return classData;
		}
		catch(Exception e)
		{
			String message = "Failed to extract class data for file: "+srcFile;
			throw new Exception(message,e);
		}
	}
}
