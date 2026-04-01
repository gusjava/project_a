package a.entity.gus06.dirfile.display;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150710";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		
		String path = f.getAbsolutePath();
    		String name = f.getName();

    		if(name.equals("")) return path;
		return name;
	}
}
