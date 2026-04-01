package a.entity.gus06.dirfile.perform.rename.ask;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20170303";}

	private Service handleFile;
	private Service handleDir;

	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.file.perform.rename.ask");
		handleDir = Outside.service(this,"gus06.dir.perform.rename.ask");
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	public boolean f(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f.isFile()) return handleFile.f(f);
		return handleDir.f(f);
	}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f.isFile()) return handleFile.t(f);
		return handleDir.t(f);
	}
}