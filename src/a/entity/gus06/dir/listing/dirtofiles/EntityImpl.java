package a.entity.gus06.dir.listing.dirtofiles;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140910";}

	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		List list = new ArrayList();
		handle(list,f);
	        return list;
	}
	
	private void handle(List list, File f)
	{
		if(f.isDirectory()) handleDir(list,f);
		else if(f.isFile()) list.add(f);
	}
	
	private void handleDir(List list, File dir)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff) handle(list,f);
	}
}