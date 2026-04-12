package a.entity.gus06.jdbc.postgresql.perform.cx.dbname;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260107";}
	
	public static final String SQL = "SELECT current_database()";

	private Service execute;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.jdbc.postgresql.perform.sqlexecute.tostring");
	}
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		return execute.t(new Object[]{cx, SQL});
	}
}