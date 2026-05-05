package a.entity.gus.x.file.filter.ext.istype.image.png;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {
	public String creationDate() {return "20150628";}

	public boolean accept(File f)
	{
		if(f==null) return false;
		if(!f.isFile()) return false;
		
		String n = f.getName().toLowerCase();
		return n.endsWith(".png");
	}
	
	public boolean f(Object obj) throws Exception
	{return accept((File)obj);}
	
	public Object g() throws Exception
	{return this;}
}