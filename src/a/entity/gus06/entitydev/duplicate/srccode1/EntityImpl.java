package a.entity.gus06.entitydev.duplicate.srccode1;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140716";}


	private Service duplicate;
	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		duplicate = Outside.service(this,"gus06.entitydev.duplicate.srccode");
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
		
		duplicate.p(map);
	}
}
