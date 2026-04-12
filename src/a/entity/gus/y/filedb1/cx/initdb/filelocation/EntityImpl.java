package a.entity.gus.y.filedb1.cx.initdb.filelocation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "filelocation";

	public static final String COL_ID = "id";
	public static final String COL_ROOT_ID = "root_id";
	public static final String COL_LOCATION = "location";
	public static final String COL_NAME = "name";
	public static final String COL_CREATED = "created";
	public static final String COL_CHECKED = "checked";
	public static final String COL_LAST_MODIFIED = "last_modified";
	public static final String COL_MD5 = "md5";
	public static final String COL_PRINT = "print";

	public static final String DEF_ID = "BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT";
	public static final String DEF_ROOT_ID = "BITINT(20) NOT NULL";
	public static final String DEF_LOCATION = "VARCHAR(2000) NOT NULL";
	public static final String DEF_NAME = "VARCHAR(400) NOT NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_CHECKED = "DATETIME NOT NULL";
	public static final String DEF_LAST_MODIFIED = "DATETIME NOT NULL";
	public static final String DEF_MD5 = "VARCHAR(32) NOT NULL";
	public static final String DEF_PRINT = "VARCHAR(32) NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_ROOT_ID+" "+DEF_ROOT_ID+", "
				+COL_LOCATION+" "+DEF_LOCATION+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_CHECKED+" "+DEF_CHECKED+", "
				+COL_LAST_MODIFIED+" "+DEF_LAST_MODIFIED+", "
				+COL_MD5+" "+DEF_MD5+", "
				+COL_PRINT+" "+DEF_PRINT + ")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
