package a.entity.gus.x.file.filter.ext.istype.properties;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {
	public String creationDate() {return "20161115";}

	public static final String EXT = "properties";

	public boolean accept(File f)
	{
		if(f==null) return false;
		if(!f.isFile()) return false;
		
		String n = f.getName().toLowerCase();
		return n.endsWith("."+EXT);
	}
	
	public boolean f(Object obj) throws Exception
	{return accept((File)obj);}
	
	public Object g() throws Exception
	{return this;}
}