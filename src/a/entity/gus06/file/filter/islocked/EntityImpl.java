package a.entity.gus06.file.filter.islocked;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20140916";}
	
	
	public boolean f(Object obj) throws Exception
	{return accept((File) obj);}
	
	
	public Object g() throws Exception
	{return this;}
	
	
	public boolean accept(File f)
	{
		File f_ = new File(f.getAbsolutePath()+"_");
		if(!f.renameTo(f_)) return true;
		f_.renameTo(f);
		return false;
	}
}