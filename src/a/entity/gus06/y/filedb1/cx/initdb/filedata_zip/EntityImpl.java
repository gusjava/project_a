package a.entity.gus06.y.filedb1.cx.initdb.filedata_zip;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "filedata_zip";

	public static final String COL_MD5 = "md5";
	public static final String COL_FILE_NB = "file_nb";
	public static final String COL_ENCODING = "encoding";
	public static final String COL_CREATED = "created";

	public static final String DEF_MD5 = "VARCHAR(32) PRIMARY KEY NOT NULL";
	public static final String DEF_FILE_NB = "INT NULL";
	public static final String DEF_ENCODING = "VARCHAR(100) NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_MD5+" "+DEF_MD5+", "
				+COL_FILE_NB+" "+DEF_FILE_NB+", "
				+COL_ENCODING+" "+DEF_ENCODING+", "
				+COL_CREATED+" "+DEF_CREATED+")";

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
// ENTITY : filedata_pdf
// ============================================================================