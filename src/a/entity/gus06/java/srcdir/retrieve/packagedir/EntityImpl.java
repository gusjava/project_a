package a.entity.gus06.java.srcdir.retrieve.packagedir;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170321";}
	
	public static final String S = File.separator;


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String package1 = (String) o[1];
		
		if(package1==null || package1.equals(""))
			throw new Exception("Invalid package: "+package1);
		
		String dirPath = dir.getAbsolutePath();
		String relPath = package1.replace(".",S);
		
		return new File(dir+S+relPath);
	}
}
