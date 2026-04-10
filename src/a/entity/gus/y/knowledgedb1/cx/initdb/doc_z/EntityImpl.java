package a.entity.gus.y.knowledgedb1.cx.initdb.doc_z;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "doc_z";
	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_NAME = "name";
	public static final String COL_SIGN = "sign";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_DIFFICULTY_LEVEL = "difficulty_level";
	public static final String COL_ISSUE_LEVEL = "issue_level";
	public static final String COL_COMMENT = "comment";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_DATE_CREATED = "DATETIME NOT NULL";
	public static final String DEF_DATE_UPDATED = "DATETIME NULL";
	public static final String DEF_NAME = "VARCHAR(200) NOT NULL UNIQUE";
	public static final String DEF_SIGN = "VARCHAR(100)";
	public static final String DEF_DESCRIPTION = "TEXT";
	public static final String DEF_STATE = "VARCHAR(20) NOT NULL";
	public static final String DEF_DIFFICULTY_LEVEL = "INT NOT NULL DEFAULT 0";
	public static final String DEF_ISSUE_LEVEL = "INT NOT NULL DEFAULT 0";
	public static final String DEF_COMMENT = "TEXT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_DATE_CREATED + " " + DEF_DATE_CREATED + ", "
				+ COL_DATE_UPDATED + " " + DEF_DATE_UPDATED + ", "
				+ COL_NAME + " " + DEF_NAME + ", "
				+ COL_SIGN + " " + DEF_SIGN + ", "
				+ COL_DESCRIPTION + " " + DEF_DESCRIPTION + ", "
				+ COL_STATE + " " + DEF_STATE + ", "
				+ COL_DIFFICULTY_LEVEL + " " + DEF_DIFFICULTY_LEVEL + ", "
				+ COL_ISSUE_LEVEL + " " + DEF_ISSUE_LEVEL + ", "
				+ COL_COMMENT + " " + DEF_COMMENT + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
