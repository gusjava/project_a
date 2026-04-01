package a.entity.gus06.file.filepath.split.all;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}

	
	
	public Object t(Object obj) throws Exception
	{
		String path = toPath(obj);
		
		if(path.startsWith("\\") || path.startsWith("/"))  path = path.substring(1);
		if(path.endsWith("\\") || path.endsWith("/"))  path = path.substring(0,path.length()-1);
		
		return path.split("[\\\\\\/]");
	}
	
	private String toPath(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getAbsolutePath();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
