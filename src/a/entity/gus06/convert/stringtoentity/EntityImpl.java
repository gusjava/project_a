package a.entity.gus06.convert.stringtoentity;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}


	private Service entityUnique;

	public EntityImpl() throws Exception
	{
		entityUnique = Outside.service(this,"entityunique");
	}
	
	public Object t(Object obj) throws Exception
	{
		return entityUnique.t(obj);
	}
}