package a.entity.gus.x.file.filter.exists.ignorecase;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, F, G, FileFilter {
	public String creationDate() {return "20250210";}
	
	public boolean f(Object obj) throws Exception
	{return accept((File) obj);}
	
	public Object g() throws Exception
	{return this;}
	
	public boolean accept(File f)
	{
		String fileName = f.getName();
		String[] names = f.getParentFile().list();
		for(String name : names) if(name.equals(fileName)) return true;
		return false;
	}
}