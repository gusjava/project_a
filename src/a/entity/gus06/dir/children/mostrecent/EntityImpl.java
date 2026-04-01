package a.entity.gus06.dir.children.mostrecent;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140706";}

	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory()) throw new Exception("Dir expected: "+dir);
		
		File[] ff = dir.listFiles();
		if(ff==null || ff.length==0) return null;
		
		File k = null;
		long l = 0;
		
		for(File f:ff) if(f.lastModified()>l)
		{
			l = f.lastModified();
			k = f;
		}
		return k;
	}
}
