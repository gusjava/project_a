package a.entity.gus.y.knowledgedb1.cx.initdb.doc_z_tag;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "doc_z_tag";
	public static final String COL_ID = "id";
	public static final String COL_TAG = "tag";

	public static final String DEF_ID = "BIGINT NOT NULL";
	public static final String DEF_TAG = "VARCHAR(50) NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_TAG + " " + DEF_TAG + ", "
				+ "PRIMARY KEY (" + COL_ID + ", " + COL_TAG + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
