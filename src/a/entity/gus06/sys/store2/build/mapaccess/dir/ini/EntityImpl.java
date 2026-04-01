package a.entity.gus06.sys.store2.build.mapaccess.dir.ini;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160309";}

	private Service dirAccessBuilder;
	private Service mapAccess;

	public EntityImpl() throws Exception
	{
		dirAccessBuilder = Outside.service(this,"gus06.dir.accessbuilder.ini");
		mapAccess = Outside.service(this,"gus06.sys.store2.build.mapaccess");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object dirAccess = dirAccessBuilder.t(obj);
		return mapAccess.t(dirAccess);
	}
}
