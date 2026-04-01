package a.entity.gus06.y.filedb1.cx.initdb.filedata_pdf;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "filedata_pdf";

	public static final String COL_MD5 = "md5";
	public static final String COL_PAGE_NB = "page_nb";
	public static final String COL_PRODUCER = "producer";
	public static final String COL_LANG = "lang";
	public static final String COL_EX_ISBN = "ex_isbn";
	public static final String COL_CREATED = "created";

	public static final String DEF_MD5 = "VARCHAR(32) PRIMARY KEY NOT NULL";
	public static final String DEF_PAGE_NB = "INT NULL";
	public static final String DEF_PRODUCER = "VARCHAR(200) NULL";
	public static final String DEF_LANG = "VARCHAR(20) NULL";
	public static final String DEF_EX_ISBN = "VARCHAR(50) NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_MD5+" "+DEF_MD5+", "
				+COL_PAGE_NB+" "+DEF_PAGE_NB+", "
				+COL_PRODUCER+" "+DEF_PRODUCER+", "
				+COL_LANG+" "+DEF_LANG+", "
				+COL_EX_ISBN+" "+DEF_EX_ISBN+", "
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
// ENTITY : filedata_ebook
// ============================================================================