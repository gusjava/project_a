package a.entity.gus06.data.perform.pickfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240423";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File)		return pickFile((File) obj);
		if(obj instanceof File[])	return pickFile((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File pickFile(File[] dirs)
	{
		for(File dir : dirs)
		{
			File f = pickFile(dir);
			if(f!=null) return f;
		}
		return null;
	}
	
	private File pickFile(File dir)
	{
		File[] ff = dir.listFiles();
		if(ff==null || ff.length==0) return null;
		
		for(File f : ff) if(f.isFile()) return f;
		for(File f : ff) if(f.isDirectory())
		{
			File c = pickFile(f);
			if(c!=null) return c;
		}
		return null;
	}
}