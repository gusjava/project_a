package a.entity.gus06.app.jarfile.extract1;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20140721";}

	
	private Service extract;
	private File srcDir;

	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.app.jarfile.extract");
		srcDir = (File) Outside.resource(this,"path#path.dev.srcdir");
	}
	
	
	public void e() throws Exception
	{
		if(srcDir==null) throw new Exception("Undefined src dir");
		extract.p(srcDir);
	}
	
	public void p(Object obj) throws Exception
	{
		F filter = (F) obj;
		if(srcDir==null) throw new Exception("Undefined src dir");
		extract.p(new Object[]{srcDir,filter});
	}
}
