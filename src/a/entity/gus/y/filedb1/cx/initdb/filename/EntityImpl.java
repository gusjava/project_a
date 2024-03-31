package a.entity.gus.y.filedb1.cx.initdb.filename;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "filename";

	public static final String COL_MD5 = 			"md5";
	public static final String COL_NAME = 			"name";
	public static final String COL_VALIDATED =		"validated";
	public static final String COL_CREATED = 		"created";

	public static final String DEF_MD5 = 			"VARCHAR(32) NOT NULL";
	public static final String DEF_NAME = 			"VARCHAR(400) NOT NULL";
	public static final String DEF_VALIDATED = 		"DATETIME NULL";
	public static final String DEF_CREATED = 		"DATETIME NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_MD5+" "+DEF_MD5+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_VALIDATED+" "+DEF_VALIDATED+", "
				+COL_CREATED+" "+DEF_CREATED
				+ ", PRIMARY KEY (" + COL_MD5 + "," + COL_NAME + "))";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
