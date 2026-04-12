package a.entity.gus06.y.entitydb1.cx.initdb.jar;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251228";}
	
	public static final String TABLE_NAME = "jar";

	public static final String COL_SHA1 = "sha1";
	public static final String COL_MD5 = "md5";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_FILE_MODIF_DATE = "file_modif_date";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_MAVEN_ID = "maven_id";
	public static final String COL_MAVEN_RETRIEVE_METHOD = "maven_retrieve_method";

	public static final String DEF_SHA1 = "VARCHAR(40) PRIMARY KEY NOT NULL";
	public static final String DEF_MD5 = "VARCHAR(32) UNIQUE NOT NULL";
	public static final String DEF_FILE_NAME = "VARCHAR(255) NOT NULL";
	public static final String DEF_FILE_MODIF_DATE = "DATETIME NOT NULL";
	public static final String DEF_CREATION_DATE = "DATETIME NOT NULL";
	public static final String DEF_MAVEN_ID = "VARCHAR(160) UNIQUE NULL";
	public static final String DEF_MAVEN_RETRIEVE_METHOD = "VARCHAR(40) NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_SHA1+" "+DEF_SHA1+", "
				+COL_MD5+" "+DEF_MD5+", "
				+COL_FILE_NAME+" "+DEF_FILE_NAME+", "
				+COL_FILE_MODIF_DATE+" "+DEF_FILE_MODIF_DATE+", "
				+COL_CREATION_DATE+" "+DEF_CREATION_DATE+", "
				+COL_MAVEN_ID+" "+DEF_MAVEN_ID+", "
				+COL_MAVEN_RETRIEVE_METHOD+" "+DEF_MAVEN_RETRIEVE_METHOD+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}