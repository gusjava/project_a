package a.entity.gus06.dir.listing.dirtopaths;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150604";}

	
	
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
			list.add(f);
			if(f.isDirectory()) handleDir(list,f);
		}
	}
}
