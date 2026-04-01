package a.entity.gus06.dir.listing0l;

import java.io.File;
import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170617";}


	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File[] ff = dir.listFiles();
		
		List list = new ArrayList();
		for(File f : ff) list.add(f);
		return list;
	}
}
