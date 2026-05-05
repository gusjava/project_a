package a.entity.gus.x.file.filter.isempty;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, F, G, FileFilter {
	public String creationDate() {return "20150628";}

	public boolean f(Object obj) throws Exception
	{return accept((File) obj);}
	
	public Object g() throws Exception
	{return this;}
	
	public boolean accept(File f)
	{
		if(f.isDirectory()) return false;
		return f.length()==0;
	}
}