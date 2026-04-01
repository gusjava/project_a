package a.entity.gus06.entitydev.find.pseudoset;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150823";}


	private Service dirToSet;
	private File rootDir;
	

	public EntityImpl() throws Exception
	{
		dirToSet = Outside.service(this,"gus06.entitydev.dirtopseudoset");
		rootDir = (File) Outside.resource(this,"path#path.dev.entityroot");
	}
	
	
	public Object g() throws Exception
	{
		if(rootDir==null) return null;
		return dirToSet.t(rootDir);
	}
}
