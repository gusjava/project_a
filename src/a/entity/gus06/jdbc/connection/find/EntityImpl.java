package a.entity.gus06.jdbc.connection.find;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190517";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Connection) return (Connection) obj;
		if(obj instanceof G) return (Connection) ((G) obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
