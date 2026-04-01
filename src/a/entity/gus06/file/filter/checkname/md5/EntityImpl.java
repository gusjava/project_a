package a.entity.gus06.file.filter.checkname.md5;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20150612";}


	private Service md5;

	public EntityImpl() throws Exception
	{md5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");}

	
	public boolean accept(File f)
	{
		try{return f(f);}
		catch(Exception e)
		{Outside.err(this,"accept(File)",e);}
		return false;
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.isFile()) return false;
		
		return file.getName().equals(md5(file));
	}
	
	
	public Object g() throws Exception
	{return this;}

	
	private String md5(File file) throws Exception
	{return (String) md5.t(file);}
}
