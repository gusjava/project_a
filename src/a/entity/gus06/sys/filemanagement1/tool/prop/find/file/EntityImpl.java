package a.entity.gus06.sys.filemanagement1.tool.prop.find.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191215";}
	
	public static final String FAILED_MD5 = "###";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dirProps = (File) o[0];
		String md5 = (String) o[1];
		
		if(md5.equals(FAILED_MD5)) return null;
		
		File dir = new File(dirProps,md5.substring(0,2));
		return new File(dir,md5+".properties");
	}
}
