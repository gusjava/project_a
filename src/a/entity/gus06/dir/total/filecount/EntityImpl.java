package a.entity.gus06.dir.total.filecount;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161117";}


	public Object t(Object obj) throws Exception
	{
		return Integer.valueOf(handle((File) obj));
	}
	
	private int handle(File file)
	{
		if(!file.exists()) return 0;
		if(file.isFile()) return 1;
		
		int count = 0;
		File[] ff = file.listFiles();
		if(ff!=null) for(File f:ff) count += handle(f);
		return count;
	}
}
