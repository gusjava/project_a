package a.entity.gus06.file.filter.ext.istype.text.xhtml;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20160527";}

	public static final String EXT = "xhtml";

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