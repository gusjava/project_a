package a.entity.gus.y.filedb1.cx.initdb.hddstate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "hddstate";

	public static final String COL_SERIAL =	"serial";
	public static final String COL_DIRS = "dirs";
	public static final String COL_USED_MEM = "used_mem";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_CREATED = "created";
	public static final String COL_UPDATED = "updated";

	public static final String DEF_SERIAL = 	"VARCHAR(32) PRIMARY KEY NOT NULL";
	public static final String DEF_DIRS = "VARCHAR(5000) NOT NULL";
	public static final String DEF_USED_MEM = "BIGINT(20) NOT NULL";
	public static final String DEF_DESCRIPTION = "VARCHAR(500) NOT NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_UPDATED = "DATETIME NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_SERIAL+" "+DEF_SERIAL+", "
				+COL_DIRS+" "+DEF_DIRS+", "
				+COL_USED_MEM+" "+DEF_USED_MEM+", "
				+COL_DESCRIPTION+" "+DEF_DESCRIPTION+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_UPDATED+" "+DEF_UPDATED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
