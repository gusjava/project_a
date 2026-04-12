package a.entity.gus06.y.entitydb1.cx.initdb.jar_jar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251228";}

	public static final String TABLE_NAME = "jar_jar";

	public static final String COL_JAR1_SHA1 = "jar1_sha1";
	public static final String COL_JAR2_SHA1 = "jar2_sha1";
	public static final String COL_TYPE = "type";

	public static final String DEF_JAR1_SHA1 = "VARCHAR(40) NOT NULL";
	public static final String DEF_JAR2_SHA1 = "VARCHAR(40) NOT NULL";
	public static final String DEF_TYPE = "VARCHAR(20) NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "CREATE TABLE " + TABLE_NAME + " ("
		 + COL_JAR1_SHA1 + " " + DEF_JAR1_SHA1 + ", "
		 + COL_JAR2_SHA1 + " " + DEF_JAR2_SHA1 + ", "
		 + COL_TYPE + " " + DEF_TYPE
		 + ", PRIMARY KEY (" + COL_JAR1_SHA1 + "," + COL_JAR2_SHA1 + "))";

		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}