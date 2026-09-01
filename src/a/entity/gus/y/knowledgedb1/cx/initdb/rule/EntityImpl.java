package a.entity.gus.y.knowledgedb1.cx.initdb.rule;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "rule";
	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_CONTENT = "content";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_CODE = "VARCHAR(50) NOT NULL UNIQUE";
	public static final String DEF_CONTENT = "TEXT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_CODE + " " + DEF_CODE + ", "
				+ COL_CONTENT + " " + DEF_CONTENT + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}