package a.entity.gus06.java.srcdir.retrieve.javafile;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160410";}
	
	public static final String S = File.separator;


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String classPath = (String) o[1];
		
		if(classPath==null || classPath.equals(""))
			throw new Exception("Invalid class path: "+classPath);
		
		String dirPath = dir.getAbsolutePath();
		String relPath = classPath.replace(".",S)+".java";
		
		return new File(dir+S+relPath);
	}
}
