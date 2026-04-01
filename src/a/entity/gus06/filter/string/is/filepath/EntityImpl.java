package a.entity.gus06.filter.string.is.filepath;

import a.framework.*;
import java.io.File;
import java.io.IOException;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230126";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		try{
			File file = new File(s);
			file.getCanonicalPath();
			return true;
		}
		catch(IOException e)
		{return false;}
	}
}