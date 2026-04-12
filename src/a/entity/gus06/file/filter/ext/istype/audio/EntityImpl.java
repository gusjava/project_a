package a.entity.gus06.file.filter.ext.istype.audio;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20250913";}

	private Service archive_mp3;
	private Service archive_wav;
	
	public EntityImpl() throws Exception
	{
		archive_mp3 = Outside.service(this,"gus06.file.filter.ext.istype.audio.mp3");
		archive_wav = Outside.service(this,"gus06.file.filter.ext.istype.audio.wav");
	}
	
	public boolean f(Object obj) throws Exception
	{return accept((File)obj);}
	
	
	public Object g() throws Exception
	{return this;}
	
	
	public boolean accept(File f)
	{
		if(f==null) return false;
		if(!f.isFile()) return false;
		
		return check(archive_mp3,f) || check(archive_wav,f);
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