package a.entity.gus.y.roadmapdb1.cx.initdb.note;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "note";
	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_TITLE = "title";
	public static final String COL_CONTENT = "content";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_DATE_CREATED = "DATETIME NOT NULL";
	public static final String DEF_TITLE = "VARCHAR(200) NOT NULL";
	public static final String DEF_CONTENT = "TEXT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_DATE_CREATED + " " + DEF_DATE_CREATED + ", "
				+ COL_TITLE + " " + DEF_TITLE + ", "
				+ COL_CONTENT + " " + DEF_CONTENT + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
