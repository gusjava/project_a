package a.entity.gus.x.file.filter.exists;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, F, G, FileFilter {
	public String creationDate() {return "20141021";}
	
	public boolean f(Object obj) throws Exception
	{return accept((File) obj);}
	
	public Object g() throws Exception
	{return this;}
	
	public boolean accept(File f)
	{return f.exists();}
}
