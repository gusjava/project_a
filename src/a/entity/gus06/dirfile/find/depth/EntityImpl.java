package a.entity.gus06.dirfile.find.depth;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170113";}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String path = file.getAbsolutePath();
		int depth = path.split(File.separator).length;
		
		return Integer.valueOf(depth);
	}
}
