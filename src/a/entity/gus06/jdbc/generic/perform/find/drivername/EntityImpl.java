package a.entity.gus06.jdbc.generic.perform.find.drivername;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190716";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		return cx.getMetaData().getDriverName();
	}
}
