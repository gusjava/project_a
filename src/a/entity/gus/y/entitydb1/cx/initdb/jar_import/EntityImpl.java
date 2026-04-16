package a.entity.gus.y.entitydb1.cx.initdb.jar_import;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260416";}

	public static final String TABLE_NAME = "jar_import";

	public static final String COL_JAR_SHA1 = "jar_sha1";
	public static final String COL_JAR_IMPORT = "jar_import";
	public static final String COL_JAR_IMPORT_PACKAGE = "jar_import_package";
	public static final String COL_JAR_IMPORT_WILDCARD = "jar_import_wildcard";

	public static final String DEF_JAR_SHA1 = "VARCHAR(40) NOT NULL";
	public static final String DEF_JAR_IMPORT = "VARCHAR(500) NOT NULL";
	public static final String DEF_JAR_IMPORT_PACKAGE = "VARCHAR(500) NOT NULL";
	public static final String DEF_JAR_IMPORT_WILDCARD = "BOOLEAN NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		{
			String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_JAR_SHA1 + " " + DEF_JAR_SHA1
				+ ", " + COL_JAR_IMPORT + " " + DEF_JAR_IMPORT
				+ ", " + COL_JAR_IMPORT_PACKAGE + " " + DEF_JAR_IMPORT_PACKAGE
				+ ", " + COL_JAR_IMPORT_WILDCARD + " " + DEF_JAR_IMPORT_WILDCARD
				+ ", PRIMARY KEY (" + COL_JAR_SHA1 + "," + COL_JAR_IMPORT + "))";

			execute(cx, sql);
		}

		{
			String sql = "CREATE INDEX idx_jar_import_wildcard ON "
				+ TABLE_NAME + "("
				+ COL_JAR_IMPORT + ","
				+ COL_JAR_IMPORT_WILDCARD + ")";

			execute(cx, sql);
		}

		{
			String sql = "CREATE INDEX idx_jar_package_wildcard ON "
				+ TABLE_NAME + "("
				+ COL_JAR_IMPORT_PACKAGE + ","
				+ COL_JAR_IMPORT_WILDCARD + ")";

			execute(cx, sql);
		}
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
