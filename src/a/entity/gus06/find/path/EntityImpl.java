package a.entity.gus06.find.path;

import a.framework.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150818";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Path) return obj;
		if(obj instanceof String) return Paths.get((String) obj);
		if(obj instanceof File) return ((File) obj).toPath();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
