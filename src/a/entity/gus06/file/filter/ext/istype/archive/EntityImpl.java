package a.entity.gus06.file.filter.ext.istype.archive;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20150628";}


	private Service archive_zip;
	private Service archive_rar;
	private Service archive_tar;
	private Service archive_ace;
	private Service archive_7z;
	

	public EntityImpl() throws Exception
	{
		archive_zip = Outside.service(this,"gus06.file.filter.ext.istype.archive.zip");
		archive_rar = Outside.service(this,"gus06.file.filter.ext.istype.archive.rar");
		archive_tar = Outside.service(this,"gus06.file.filter.ext.istype.archive.tar");
		archive_ace = Outside.service(this,"gus06.file.filter.ext.istype.archive.ace");
		archive_7z = Outside.service(this,"gus06.file.filter.ext.istype.archive.f7z");
	}

	
	public boolean f(Object obj) throws Exception
	{return accept((File)obj);}
	
	
	
	public Object g() throws Exception
	{return this;}

	
	
	public boolean accept(File f)
	{
		if(f==null) return false;
		if(!f.isFile()) return false;
		
		return check(archive_zip,f) 
				|| check(archive_rar,f) 
				|| check(archive_tar,f) 
				|| check(archive_ace,f) 
				|| check(archive_7z,f);
	}
	
	
	private boolean check(Service s, File f)
	{
		try{return s.f(f);}
		catch (Exception e)
		{
			Outside.err(this,"check(Service,File)",e);
			return false;
		}
	}
}
