package a.entity.gus06.entitydev.generate.srccode1;

import java.io.File;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140712";}

	
	private Service generate;
	private File rootDir;
	

	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.entitydev.generate.srccode");
		rootDir = (File) Outside.resource(this,"path#path.dev.entityroot");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		if(rootDir==null)
			throw new Exception("Unknown root path for entity src code");
		map.put(GEN.KEY_ROOTDIR,rootDir.getAbsolutePath());
		
		generate.p(map);
	}
}
