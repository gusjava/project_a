package a.entity.gus.y.knowledgedb1.cx.initdb.doc_y_index;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_index";
	public static final String COL_DOC_Y_ID = "doc_y_id";
	public static final String COL_MEMBER_KEY = "member_key";
	public static final String COL_DESCRIPTION = "description";

	public static final String DEF_DOC_Y_ID = "BIGINT NOT NULL";
	public static final String DEF_MEMBER_KEY = "VARCHAR(200) NOT NULL";
	public static final String DEF_DESCRIPTION = "TEXT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_DOC_Y_ID + " " + DEF_DOC_Y_ID + ", "
				+ COL_MEMBER_KEY + " " + DEF_MEMBER_KEY + ", "
				+ COL_DESCRIPTION + " " + DEF_DESCRIPTION + ", "
				+ "PRIMARY KEY (" + COL_DOC_Y_ID + ", " + COL_MEMBER_KEY + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}