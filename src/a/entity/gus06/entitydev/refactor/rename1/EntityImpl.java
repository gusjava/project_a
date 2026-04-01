package a.entity.gus06.entitydev.refactor.rename1;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140715";}


	private Service refactor;
	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		refactor = Outside.service(this,"gus06.entitydev.refactor.rename");
		rootDir = (File) Outside.resource(this,"path#path.dev.entityroot");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String oldName = o[0];
		String newName = o[1];
		
		Map map = new HashMap();
		
		if(rootDir==null)
			throw new Exception("Unknown root path for entity src code");
		map.put(REFACTOR.KEY_ROOTDIR,rootDir.getAbsolutePath());

		map.put(REFACTOR.KEY_ENTITYNAME1,oldName);
		map.put(REFACTOR.KEY_ENTITYNAME2,newName);
		
		refactor.p(map);
	}
}
