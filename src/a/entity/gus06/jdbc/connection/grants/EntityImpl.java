package a.entity.gus06.jdbc.connection.grants;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150622";}


	private Service findGrants;
	
	public EntityImpl() throws Exception
	{
		findGrants = Outside.service(this,"gus06.jdbc.generic.perform.showgrants");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return findGrants.t(obj);
	}
}
