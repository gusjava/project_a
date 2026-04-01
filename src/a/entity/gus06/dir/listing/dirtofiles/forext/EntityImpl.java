package a.entity.gus06.dir.listing.dirtofiles.forext;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150629";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String ext = ((String) o[1]).toLowerCase();
		
		List list = new ArrayList();
    		handleDir(list,dir,ext);
		return list;
	}
	
	private void handleDir(List list, File dir, String ext)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(list,f,ext);
			else if(isTarget(f,ext)) list.add(f);
		}
	}
	
	private boolean isTarget(File f, String ext)
	{
		String name = f.getName().toLowerCase();
		return name.endsWith("."+ext);
	}
}
