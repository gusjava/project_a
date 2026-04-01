package a.entity.gus06.dir.info.listing.dirtopaths;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170720";}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		StringBuffer b = new StringBuffer();
    		handleDir(b,dir);
        	return b.toString().trim();
	}
	
	
	private void handleDir(StringBuffer b, File dir)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			b.append(f+"\n");
			if(f.isDirectory()) handleDir(b,f);
		}
	}
}