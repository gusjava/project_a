package a.entity.gus06.dir.listing.dirtofiles.forext2;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220401";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String ext = ((String) o[1]).toLowerCase();
		String[] exts = ext.split(";");
		
		List list = new ArrayList();
    		handleDir(list,dir,exts);
        	return list;
	}
	
	
	private void handleDir(List list, File dir, String[] exts)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(list,f,exts);
			else if(isTarget(f,exts)) list.add(f);
		}
	}
	
	
	private boolean isTarget(File f, String[] exts)
	{
		String name = f.getName().toLowerCase();
		for(String ext : exts) if(name.endsWith("."+ext)) return true;
		return false;
	}
}