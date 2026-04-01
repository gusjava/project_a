package a.entity.gus06.jdbc.mysql.perform.table.truncate.cascade;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190726";}


	
	public EntityImpl() throws Exception
	{
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		throw new Exception("TRUNCATE CASCADE is not supported for MySQL");
	}
}