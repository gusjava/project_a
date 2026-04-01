package a.entity.gus06.dirfile.perform.remove;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140930";}


	private Service handleFile;
	private Service handleDir;


	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.file.perform.remove");
		handleDir = Outside.service(this,"gus06.dir.perform.remove");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f.isFile()) return handleFile.f(f);
		return handleDir.f(f);
	}
}
