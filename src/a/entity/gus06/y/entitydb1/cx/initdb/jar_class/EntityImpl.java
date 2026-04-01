package a.entity.gus06.y.entitydb1.cx.initdb.jar_class;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251228";}

	public static final String TABLE_NAME = "jar_class";

	public static final String COL_JAR_SHA1 = "jar_sha1";
	public static final String COL_JAR_CLASS = "jar_class";
	public static final String COL_JAR_CLASS_PACKAGE = "jar_class_package";

	public static final String DEF_JAR_SHA1 = "VARCHAR(40) NOT NULL";
	public static final String DEF_JAR_CLASS = "VARCHAR(500) NOT NULL";
	public static final String DEF_JAR_CLASS_PACKAGE = "VARCHAR(500) NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		{
			String sql = "CREATE TABLE " + TABLE_NAME + " ("
			 + COL_JAR_SHA1 + " " + DEF_JAR_SHA1 + ", "
			 + COL_JAR_CLASS + " " + DEF_JAR_CLASS + ", "
			 + COL_JAR_CLASS_PACKAGE + " " + DEF_JAR_CLASS_PACKAGE
			 + ", PRIMARY KEY (" + COL_JAR_SHA1 + "," + COL_JAR_CLASS + "))";
	
			execute(cx, sql);
		}
		
		{
			String sql = "CREATE INDEX idx_jar_class_package ON " 
			 + TABLE_NAME + "("
			 + COL_JAR_CLASS_PACKAGE + ")";
	
			execute(cx, sql);
		}
	}

	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}