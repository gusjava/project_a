package a.entity.gus06.jdbc.mysql.perform.cx.dbname;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231017";}
	
	public static final String SQL = "SELECT DATABASE()";

	private Service execute;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute.tostring");
	}
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		return execute.t(new Object[]{cx, SQL});
	}
}