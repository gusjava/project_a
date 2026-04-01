package a.entity.gus06.java.compiler1.bin.empty;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200304";}


	private Service emptyDir;
	private File binDir;

	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
		binDir = (File) Outside.resource(this,"path#path.dev.bindir");
	}
	
	public void e() throws Exception
	{
		emptyDir.p(binDir);
	}
}
