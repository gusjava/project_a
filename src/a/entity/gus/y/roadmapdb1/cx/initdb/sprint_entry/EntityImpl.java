package a.entity.gus.y.roadmapdb1.cx.initdb.sprint_entry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "sprint_entry";
	public static final String COL_ID = "id";
	public static final String COL_ID_SPRINT = "id_sprint";
	public static final String COL_DATE = "date";
	public static final String COL_ID_TASK = "id_task";
	public static final String COL_DESCRIPTION = "description";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_ID_SPRINT = "BIGINT NOT NULL";
	public static final String DEF_DATE = "DATE NOT NULL";
	public static final String DEF_ID_TASK = "BIGINT";
	public static final String DEF_DESCRIPTION = "TEXT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_ID_SPRINT + " " + DEF_ID_SPRINT + ", "
				+ COL_DATE + " " + DEF_DATE + ", "
				+ COL_ID_TASK + " " + DEF_ID_TASK + ", "
				+ COL_DESCRIPTION + " " + DEF_DESCRIPTION + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
