package a.entity.gus06.y.filedb1.cx.initdb.hddstate;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "hddstate";

	public static final String COL_SERIAL = "serial";
	public static final String COL_CREATED = "created";
	public static final String COL_UPDATED = "updated";
	public static final String COL_DISABLED = "disabled";
	public static final String COL_USED_BYTES = "used_bytes";
	public static final String COL_DIRS = "dirs";
	public static final String COL_DESCRIPTION = "description";

	public static final String DEF_SERIAL = "VARCHAR(10) PRIMARY KEY NOT NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_UPDATED = "DATETIME NULL";
	public static final String DEF_DISABLED = "BOOLEAN NULL";
	public static final String DEF_USED_BYTES = "BIGINT NULL";
	public static final String DEF_DIRS = "INT NULL";
	public static final String DEF_DESCRIPTION = "VARCHAR(500) NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_SERIAL+" "+DEF_SERIAL+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_UPDATED+" "+DEF_UPDATED+", "
				+COL_DISABLED+" "+DEF_DISABLED+", "
				+COL_USED_BYTES+" "+DEF_USED_BYTES+", "
				+COL_DIRS+" "+DEF_DIRS+", "
				+COL_DESCRIPTION+" "+DEF_DESCRIPTION+")";
		
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
// ENTITY : root
// ============================================================================