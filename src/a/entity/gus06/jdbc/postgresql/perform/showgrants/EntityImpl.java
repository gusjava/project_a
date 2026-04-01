package a.entity.gus06.jdbc.postgresql.perform.showgrants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20190717";}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Connection cx = (Connection) obj;
		DatabaseMetaData dbmd = cx.getMetaData();
		String user = dbmd.getUserName();
		
		return user.equals("postgre") ? user+"*" : user; 
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		
		Connection cx = (Connection) obj;
		DatabaseMetaData dbmd = cx.getMetaData();
		String user = dbmd.getUserName();
		
		return user.equals("postgre");
	}
}
