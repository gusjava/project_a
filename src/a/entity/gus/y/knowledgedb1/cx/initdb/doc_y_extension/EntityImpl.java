package a.entity.gus.y.knowledgedb1.cx.initdb.doc_y_extension;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_extension";
	public static final String COL_ID = "id";
	public static final String COL_UNIT_Y_ID = "unit_y_id";
	public static final String COL_NAME = "name";
	public static final String COL_KIND = "kind";
	public static final String COL_DIRECTION = "direction";
	public static final String COL_ENTRY_POINT = "entry_point";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_SPEC_ID = "spec_id";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_UNIT_Y_ID = "BIGINT NOT NULL";
	public static final String DEF_NAME = "VARCHAR(100) NOT NULL";
	public static final String DEF_KIND = "VARCHAR(20) NOT NULL";
	public static final String DEF_DIRECTION = "VARCHAR(20)";
	public static final String DEF_ENTRY_POINT = "VARCHAR(200)";
	public static final String DEF_DESCRIPTION = "TEXT";
	public static final String DEF_SPEC_ID = "BIGINT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_UNIT_Y_ID + " " + DEF_UNIT_Y_ID + ", "
				+ COL_NAME + " " + DEF_NAME + ", "
				+ COL_KIND + " " + DEF_KIND + ", "
				+ COL_DIRECTION + " " + DEF_DIRECTION + ", "
				+ COL_ENTRY_POINT + " " + DEF_ENTRY_POINT + ", "
				+ COL_DESCRIPTION + " " + DEF_DESCRIPTION + ", "
				+ COL_SPEC_ID + " " + DEF_SPEC_ID + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}