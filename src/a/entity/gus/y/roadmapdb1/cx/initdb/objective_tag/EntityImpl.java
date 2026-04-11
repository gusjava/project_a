package a.entity.gus.y.roadmapdb1.cx.initdb.objective_tag;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "objective_tag";
	public static final String COL_ID_OBJECTIVE = "id_objective";
	public static final String COL_TAG = "tag";

	public static final String DEF_ID_OBJECTIVE = "BIGINT NOT NULL";
	public static final String DEF_TAG = "VARCHAR(50) NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID_OBJECTIVE + " " + DEF_ID_OBJECTIVE + ", "
				+ COL_TAG + " " + DEF_TAG + ", "
				+ "PRIMARY KEY (" + COL_ID_OBJECTIVE + ", " + COL_TAG + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
