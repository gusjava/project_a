package a.entity.gus06.jdbc.mysql.perform.find.user.current;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161101";}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Connection cx = (Connection) obj;
		DatabaseMetaData dbmd = cx.getMetaData();
		return dbmd.getUserName();
	}
}
