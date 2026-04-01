package a.entity.gus06.sys.store2.build.mapaccess2.dir.ini;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160313";}

	private Service dirAccessBuilder;
	private Service mapAccess;

	public EntityImpl() throws Exception
	{
		dirAccessBuilder = Outside.service(this,"gus06.dir.accessbuilder2.ini");
		mapAccess = Outside.service(this,"gus06.sys.store2.build.mapaccess2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object dirAccess = dirAccessBuilder.t(obj);
		return mapAccess.t(dirAccess);
	}
}
