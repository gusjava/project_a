package a.entity.gus06.dir.listing.dir1tofiles;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200310";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList();
		
		if(obj instanceof File)
		{
			File p = (File) obj;
			handleDir(list,p);
		}
		else if(obj instanceof File[])
		{
			File[] pp = (File[]) obj;
			for(File p : pp)
			handleDir(list,p);
		}
		else if(obj instanceof List)
		{
			List pp = (List) obj;
			for(Object p : pp)
			handleDir(list,(File) p);
		}
		return list;
	}
	
	
	private void handle(List list, File p)
	{
		if(p.isDirectory()) handleDir(list,p);
		else list.add(p);
	}
	
	private void handleDir(List list, File dir)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff) handle(list,f);
	}
}