package a.entity.gus.y.knowledgedb1.cx.initdb.doc_x_spec;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_x_spec";
	public static final String COL_DOC_X_ID = "doc_x_id";
	public static final String COL_SPEC_ID = "spec_id";

	public static final String DEF_DOC_X_ID = "BIGINT NOT NULL";
	public static final String DEF_SPEC_ID = "BIGINT NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_DOC_X_ID + " " + DEF_DOC_X_ID + ", "
				+ COL_SPEC_ID + " " + DEF_SPEC_ID + ", "
				+ "PRIMARY KEY (" + COL_DOC_X_ID + ", " + COL_SPEC_ID + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}