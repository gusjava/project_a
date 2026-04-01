package a.entity.gus06.sys.xhtml1.webroot.find;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220907";}
	
	public static final String WEB_INF = "WEB-INF";
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		while(file!=null && !isWebRoot(file))
		file = file.getParentFile();
		return file;
	}
	
	
	private boolean isWebRoot(File file)
	{return new File(file,WEB_INF).isDirectory();}
}
