package a.entity.gus06.appli.gusclient1.execute.space.entities.backup;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150917";}


	
	private File backupDir;
	private File entityDir;

	
	public EntityImpl() throws Exception
	{
		backupDir = (File) Outside.resource(this,"entitybackupdir");
		entityDir = (File) Outside.resource(this,"entitysrcdir");
	}
	
	public void e() throws Exception
	{
		// backup all entities from entityDir to backupDir ...
	}
}
