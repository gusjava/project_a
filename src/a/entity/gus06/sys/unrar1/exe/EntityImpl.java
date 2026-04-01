package a.entity.gus06.sys.unrar1.exe;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20251108";}

	public static final String EXENAME = "UnRAR.exe";

	private Service initRoot;
	private File rootDir;
	private File exeFile;
	
	public EntityImpl() throws Exception
	{
		initRoot = Outside.service(this,"gus06.sys.unrar1.init");
		rootDir = (File) Outside.resource(this,"path#path.unrar1.rootdir");
	}
	
	public Object g() throws Exception
	{
		if(exeFile==null) init();
		return exeFile;
	}
	
	private void init() throws Exception
	{
		exeFile = new File(rootDir, EXENAME);
		if(!exeFile.exists()) initRoot.p(rootDir);
		if(!exeFile.exists()) throw new Exception("Failed to init unrar root dir: "+rootDir);
	}
}