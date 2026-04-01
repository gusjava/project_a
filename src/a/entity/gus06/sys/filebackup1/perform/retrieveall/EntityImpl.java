package a.entity.gus06.sys.filebackup1.perform.retrieveall;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161018";}



	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		Set set = new HashSet();
		File[] ff = dir.listFiles();
		for(File f:ff)
		{
			String[] n = f.getName().split("_");
			set.add(n[0]+"_"+n[1]);
		}
		return set;
	}
}
