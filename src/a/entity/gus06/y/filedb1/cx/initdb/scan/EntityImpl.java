package a.entity.gus06.y.filedb1.cx.initdb.scan;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "scan";

	public static final String COL_ROOT_ID = "root_id";
	public static final String COL_MODULES = "modules";
	public static final String COL_TIME = "time";
	public static final String COL_DURATION = "duration";
	public static final String COL_RESULT = "result";
	public static final String COL_TOTAL_SIZE = "total_size";
	public static final String COL_FILE_NB = "file_nb";
	public static final String COL_EXCEPTION = "exception";

	public static final String DEF_ROOT_ID = "VARCHAR(50) NOT NULL";
	public static final String DEF_MODULES = "VARCHAR(200) NULL";
	public static final String DEF_TIME = "DATETIME NOT NULL";
	public static final String DEF_DURATION = "BIGINT NULL";
	public static final String DEF_RESULT = "VARCHAR(500) NULL";
	public static final String DEF_TOTAL_SIZE = "BIGINT NULL";
	public static final String DEF_FILE_NB = "INT NULL";
	public static final String DEF_EXCEPTION = "VARCHAR(2000) NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ROOT_ID+" "+DEF_ROOT_ID+", "
				+COL_MODULES+" "+DEF_MODULES+", "
				+COL_TIME+" "+DEF_TIME+", "
				+COL_DURATION+" "+DEF_DURATION+", "
				+COL_RESULT+" "+DEF_RESULT+", "
				+COL_TOTAL_SIZE+" "+DEF_TOTAL_SIZE+", "
				+COL_FILE_NB+" "+DEF_FILE_NB+", "
				+COL_EXCEPTION+" "+DEF_EXCEPTION+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}