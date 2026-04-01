package a.entity.gus06.dirfile.perform.rename.timestamped;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T, F {

	public String creationDate() {return "20170303";}


	private Service handleFile;
	private Service handleDir;


	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.file.perform.rename.timestamped");
		handleDir = Outside.service(this,"gus06.dir.perform.rename.timestamped");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f.isFile()) return handleFile.t(f);
		return handleDir.t(f);
	}
}
