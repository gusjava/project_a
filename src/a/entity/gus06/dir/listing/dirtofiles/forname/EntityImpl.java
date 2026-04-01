package a.entity.gus06.dir.listing.dirtofiles.forname;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160927";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String name = (String) o[1];
		
		List list = new ArrayList();
    		handleDir(list,dir,name);
        	return list;
	}
	
	
	private void handleDir(List list, File dir, String name)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(list,f,name);
			else if(isTarget(f,name)) list.add(f);
		}
	}
	
	
	private boolean isTarget(File f, String name)
	{
		return f.getName().equals(name);
	}
}
