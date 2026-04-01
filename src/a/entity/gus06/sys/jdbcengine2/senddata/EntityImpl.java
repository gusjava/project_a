package a.entity.gus06.sys.jdbcengine2.senddata;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170331";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
	}
}
