package a.entity.gus06.filter.string.is.filepath.dir;

import a.framework.*;
import java.io.File;
import java.io.IOException;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231218";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		try{
			File file = new File(s);
			file.getCanonicalPath();
			return file.isDirectory();
		}
		catch(IOException e)
		{return false;}
	}
}