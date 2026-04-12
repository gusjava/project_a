package a.entity.gus06.dir.info.listing.dirtopaths.relpath;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240620";}

	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int len = dir.getAbsolutePath().length()+1;
		
		StringBuffer b = new StringBuffer();
    		handleDir(b,dir,len);
		return b.toString().trim();
	}
	
	private void handleDir(StringBuffer b, File dir, int len)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			String path = f.getAbsolutePath().substring(len);
			b.append(path+"\n");
			if(f.isDirectory()) handleDir(b,f,len);
		}
	}
}