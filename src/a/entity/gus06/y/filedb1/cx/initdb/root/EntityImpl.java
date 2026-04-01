package a.entity.gus06.y.filedb1.cx.initdb.root;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "root";

	public static final String COL_ID = "id";
	public static final String COL_SERIAL = "serial";
	public static final String COL_LOCATION = "location";
	public static final String COL_NAME = "name";
	public static final String COL_CREATED = "created";
	public static final String COL_UPDATED = "updated";
	public static final String COL_DISABLED = "disabled";

	public static final String DEF_ID = "VARCHAR(50) PRIMARY KEY NOT NULL";
	public static final String DEF_SERIAL = "VARCHAR(10) NOT NULL";
	public static final String DEF_LOCATION = "VARCHAR(50) NOT NULL";
	public static final String DEF_NAME = "VARCHAR(200) NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_UPDATED = "DATETIME NULL";
	public static final String DEF_DISABLED = "BOOLEAN NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_SERIAL+" "+DEF_SERIAL+", "
				+COL_LOCATION+" "+DEF_LOCATION+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_UPDATED+" "+DEF_UPDATED+", "
				+COL_DISABLED+" "+DEF_DISABLED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}


// ============================================================================
// ENTITY : scan
// ============================================================================