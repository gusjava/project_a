package a.entity.gus.y.roadmapdb1.cx.initdb.sprint;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "sprint";
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_DATE_START = "date_start";
	public static final String COL_DATE_END = "date_end";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_NAME = "VARCHAR(100) NOT NULL";
	public static final String DEF_DATE_START = "DATE NOT NULL";
	public static final String DEF_DATE_END = "DATE NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_NAME + " " + DEF_NAME + ", "
				+ COL_DATE_START + " " + DEF_DATE_START + ", "
				+ COL_DATE_END + " " + DEF_DATE_END + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
