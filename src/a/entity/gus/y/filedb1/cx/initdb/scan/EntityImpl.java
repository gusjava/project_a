package a.entity.gus.y.filedb1.cx.initdb.scan;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "scan";

	public static final String COL_ROOT_ID = "root_id";
	public static final String COL_TIME = "time";
	public static final String COL_MODULES = "modules";
	public static final String COL_DURATION = "duration";
	public static final String COL_RESULT = "result";
	public static final String COL_EXCEPTION = "exception";
	public static final String COL_TOTAL_SIZE = "total_size";
	public static final String COL_FILE_NB = "file_nb";

	public static final String DEF_ROOT_ID = "BIGINT(20) NOT NULL";
	public static final String DEF_TIME = "DATETIME NOT NULL";
	public static final String DEF_MODULES = "VARCHAR(10) NULL";
	public static final String DEF_DURATION = "BIGINT(20) NULL";
	public static final String DEF_RESULT = "VARCHAR(10) NULL";
	public static final String DEF_EXCEPTION = "VARCHAR(2000) NULL";
	public static final String DEF_TOTAL_SIZE = "BIGINT(20) NULL";
	public static final String DEF_FILE_NB = "BIGINT(20) NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_ROOT_ID+" "+DEF_ROOT_ID+", "
				+COL_TIME+" "+DEF_TIME+", "
				+COL_MODULES+" "+DEF_MODULES+", "
				+COL_DURATION+" "+DEF_DURATION+", "
				+COL_RESULT+" "+DEF_RESULT+", "
				+COL_EXCEPTION+" "+DEF_EXCEPTION+", "
				+COL_TOTAL_SIZE+" "+DEF_TOTAL_SIZE+", "
				+COL_FILE_NB+" "+DEF_FILE_NB
				+ ", PRIMARY KEY (" + COL_ROOT_ID + "," + COL_TIME + "))";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
