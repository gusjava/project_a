package a.entity.gus.y.knowledgedb1.cx.initdb.doc_y_entry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_entry";
	public static final String COL_DOC_Y_ID = "doc_y_id";
	public static final String COL_ENTITY_NAME = "entity_name";

	public static final String DEF_DOC_Y_ID = "BIGINT NOT NULL";
	public static final String DEF_ENTITY_NAME = "VARCHAR(200) NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_DOC_Y_ID + " " + DEF_DOC_Y_ID + ", "
				+ COL_ENTITY_NAME + " " + DEF_ENTITY_NAME + ", "
				+ "PRIMARY KEY (" + COL_DOC_Y_ID + ", " + COL_ENTITY_NAME + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}