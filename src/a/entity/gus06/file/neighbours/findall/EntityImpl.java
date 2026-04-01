package a.entity.gus06.file.neighbours.findall;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150923";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File parent = file.getParentFile();
		
		List l = new ArrayList();
		File[] ff = parent.listFiles();
		for(File f:ff) if(!f.equals(file)) l.add(f);
		
		return l;
	}
}
