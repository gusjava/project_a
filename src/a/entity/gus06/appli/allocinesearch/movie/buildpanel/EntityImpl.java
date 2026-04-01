package a.entity.gus06.appli.allocinesearch.movie.buildpanel;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150210";}
	
	public static final String ENTITY = "gus.appli.allocinesearch.movie.buildpanel.holder";


	private Service newEntity;

	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object viewer = newEntity.t(ENTITY);
		((P)viewer).p(obj);
		return ((I)viewer).i();
	}
}
