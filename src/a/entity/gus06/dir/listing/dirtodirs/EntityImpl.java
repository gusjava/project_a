package a.entity.gus06.dir.listing.dirtodirs;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150817";}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		List list = new ArrayList();
    		handleDir(list,dir);
        	return list;
	}
	
	
	private void handleDir(List list, File dir)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory())
			{
				list.add(f);
				handleDir(list,f);
			}
		}
	}
}
