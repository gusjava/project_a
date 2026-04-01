package a.entity.gus06.data.viewer.object.factory.comp;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191125";}


	private Service factory;

	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.data.viewer.object.factory");
	}
	
	public Object t(Object obj) throws Exception
	{
		I viewer = (I) factory.t(obj);
		return viewer.i();
	}
}
