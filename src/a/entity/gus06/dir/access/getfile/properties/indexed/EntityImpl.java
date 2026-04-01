package a.entity.gus06.dir.access.getfile.properties.indexed;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161008";}
	
	public static final String EXTENSION = "properties";

	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.exists()) dir.mkdirs();
		
		String name = "" + dir.list().length;
		while(name.length()<8) name = "0"+name;
		
		return new File(dir,name+"."+EXTENSION);
	}
}
