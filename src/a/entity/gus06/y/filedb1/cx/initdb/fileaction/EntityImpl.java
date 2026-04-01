package a.entity.gus06.y.filedb1.cx.initdb.fileaction;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "fileaction";

	public static final String COL_ID = "id";
	public static final String COL_ROOT_ID = "root_id";
	public static final String COL_LOCATION = "location";
	public static final String COL_NAME = "name";
	public static final String COL_MD5_CONDITION = "md5_condition";
	public static final String COL_ACTION = "action";
	public static final String COL_INFO = "info";
	public static final String COL_CREATED = "created";
	public static final String COL_EXECUTED = "executed";
	public static final String COL_RESULT = "result";

	public static final String DEF_ID = "VARCHAR(50) PRIMARY KEY NOT NULL";
	public static final String DEF_ROOT_ID = "VARCHAR(50) NOT NULL";
	public static final String DEF_LOCATION = "VARCHAR(500) NOT NULL";
	public static final String DEF_NAME = "VARCHAR(500) NOT NULL";
	public static final String DEF_MD5_CONDITION = "VARCHAR(32) NULL";
	public static final String DEF_ACTION = "VARCHAR(200) NULL";
	public static final String DEF_INFO = "VARCHAR(2000) NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_EXECUTED = "DATETIME NULL";
	public static final String DEF_RESULT = "VARCHAR(2000) NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_ROOT_ID+" "+DEF_ROOT_ID+", "
				+COL_LOCATION+" "+DEF_LOCATION+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_MD5_CONDITION+" "+DEF_MD5_CONDITION+", "
				+COL_ACTION+" "+DEF_ACTION+", "
				+COL_INFO+" "+DEF_INFO+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_EXECUTED+" "+DEF_EXECUTED+", "
				+COL_RESULT+" "+DEF_RESULT+")";
		
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
// ENTITY : fileaction_history
// ============================================================================