package a.entity.gus06.tostring.desc.short1.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}
	


	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		return f.getAbsolutePath()+" ["+infos(f)+"]";
	}
	
	private String infos(File f)
	{
		if(f.isDirectory()) return "dir";
		if(f.isFile()) return "file";
		return "not found";
	}
}
