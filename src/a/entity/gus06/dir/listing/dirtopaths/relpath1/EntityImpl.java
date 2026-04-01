package a.entity.gus06.dir.listing.dirtopaths.relpath1;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180413";}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int len = dir.getAbsolutePath().length()+1;
		
		List list = new ArrayList();
    		handleDir(list,dir,len);
        	return list;
	}
	
	
	private void handleDir(List list, File dir, int len)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			list.add(f.getAbsolutePath().substring(len)+info(f));
			if(f.isDirectory()) handleDir(list,f,len);
		}
	}
	
	private String info(File f)
	{
		if(f.isFile()) return " ["+f.length()+"]";
		return "";
	}
}
