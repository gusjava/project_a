package a.entity.gus06.y.entitydb1.cx.initdb.entity_service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251111";}

	public static final String TABLE_NAME = "entity_service";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_CALL = "call";

	public static final String DEF_ENTITY_NAME = "VARCHAR(200) NOT NULL";
	public static final String DEF_CALL = "VARCHAR(500) NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "CREATE TABLE " + TABLE_NAME + " ("
		 + COL_ENTITY_NAME + " " + DEF_ENTITY_NAME + ", "
		 + COL_CALL + " " + DEF_CALL
		 + ", PRIMARY KEY (" + COL_ENTITY_NAME + "," + COL_CALL + "))";

		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}