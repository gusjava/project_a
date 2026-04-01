package a.entity.gus06.y.filedb1.cx.initdb.filedata_ebook;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "filedata_ebook";

	public static final String COL_MD5 = "md5";
	public static final String COL_AUTHOR = "author";
	public static final String COL_TITLE = "title";
	public static final String COL_SUMMARY = "summary";
	public static final String COL_PUBLISHER = "publisher";
	public static final String COL_PUBLISHED_DATE = "published_date";
	public static final String COL_LANG = "lang";
	public static final String COL_ISBN = "isbn";
	public static final String COL_CREATED = "created";
	public static final String COL_TYPE = "type";
	public static final String COL_RESULT = "result";

	public static final String DEF_MD5 = "VARCHAR(32) PRIMARY KEY NOT NULL";
	public static final String DEF_AUTHOR = "VARCHAR(300) NULL";
	public static final String DEF_TITLE = "VARCHAR(500) NULL";
	public static final String DEF_SUMMARY = "TEXT NULL";
	public static final String DEF_PUBLISHER = "VARCHAR(300) NULL";
	public static final String DEF_PUBLISHED_DATE = "VARCHAR(30) NULL";
	public static final String DEF_LANG = "VARCHAR(20) NULL";
	public static final String DEF_ISBN = "VARCHAR(50) NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_TYPE = "VARCHAR(20) NULL";
	public static final String DEF_RESULT = "VARCHAR(20) NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_MD5+" "+DEF_MD5+", "
				+COL_AUTHOR+" "+DEF_AUTHOR+", "
				+COL_TITLE+" "+DEF_TITLE+", "
				+COL_SUMMARY+" "+DEF_SUMMARY+", "
				+COL_PUBLISHER+" "+DEF_PUBLISHER+", "
				+COL_PUBLISHED_DATE+" "+DEF_PUBLISHED_DATE+", "
				+COL_LANG+" "+DEF_LANG+", "
				+COL_ISBN+" "+DEF_ISBN+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_TYPE+" "+DEF_TYPE+", "
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