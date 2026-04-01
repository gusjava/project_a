package a.entity.gus06.dir.hdd.space.usable;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170102";}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		long size = file.getUsableSpace();
		return Long.valueOf(size);
	}
}
