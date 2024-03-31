package a.entity.gus.y.filedb1.cx.initdb.file;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "file";

	public static final String COL_MD5 = 			"md5";
	public static final String COL_SIZE = 			"size";
	public static final String COL_MIME =	 		"mime";
	public static final String COL_EXT = 			"ext";
	public static final String COL_CREATED = 		"created";

	public static final String DEF_MD5 = 			"VARCHAR(32) PRIMARY KEY NOT NULL";
	public static final String DEF_SIZE = 			"BIGINT(20) NOT NULL";
	public static final String DEF_MIME = 			"VARCHAR(100) NOT NULL";
	public static final String DEF_EXT = 			"VARCHAR(20) NOT NULL";
	public static final String DEF_CREATED = 		"DATETIME NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_MD5+" "+DEF_MD5+", "
				+COL_SIZE+" "+DEF_SIZE+", "
				+COL_MIME+" "+DEF_MIME+", "
				+COL_EXT+" "+DEF_EXT+", "
				+COL_CREATED+" "+DEF_CREATED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
