package a.entity.gus06.sys.clipboard1.writecontent;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20200430";}


	private Service toDir;
	private Service toFile;

	public EntityImpl() throws Exception
	{
		toDir = Outside.service(this,"gus06.sys.clipboard1.writecontent.todir");
		toFile = Outside.service(this,"gus06.sys.clipboard1.writecontent.tofile");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file.isFile()) return toFile.f(file);
		if(file.isDirectory()) return toDir.f(file);
		return toFile.f(file);
	}
}
