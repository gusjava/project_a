package a.entity.gus.y.roadmapdb1.cx.initdb.task;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "task";
	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_ID_OBJECTIVE = "id_objective";
	public static final String COL_TYPE = "type";
	public static final String COL_STATUS = "status";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_DIFFICULTY_ESTIMATED = "difficulty_estimated";
	public static final String COL_DIFFICULTY_REAL = "difficulty_real";
	public static final String COL_DURATION_ESTIMATED = "duration_estimated";
	public static final String COL_DURATION_REAL = "duration_real";
	public static final String COL_DATE_START_ESTIMATED = "date_start_estimated";
	public static final String COL_DATE_END_ESTIMATED = "date_end_estimated";
	public static final String COL_DATE_START_REAL = "date_start_real";
	public static final String COL_DATE_END_REAL = "date_end_real";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_CODE = "CHAR(4) NOT NULL UNIQUE";
	public static final String DEF_DATE_CREATED = "DATETIME NOT NULL";
	public static final String DEF_ID_OBJECTIVE = "BIGINT";
	public static final String DEF_TYPE = "VARCHAR(10) NOT NULL";
	public static final String DEF_STATUS = "VARCHAR(20) NOT NULL";
	public static final String DEF_TITLE = "VARCHAR(200) NOT NULL";
	public static final String DEF_DESCRIPTION = "TEXT";
	public static final String DEF_DIFFICULTY_ESTIMATED = "INT";
	public static final String DEF_DIFFICULTY_REAL = "INT";
	public static final String DEF_DURATION_ESTIMATED = "INT";
	public static final String DEF_DURATION_REAL = "INT";
	public static final String DEF_DATE_START_ESTIMATED = "DATETIME";
	public static final String DEF_DATE_END_ESTIMATED = "DATETIME";
	public static final String DEF_DATE_START_REAL = "DATETIME";
	public static final String DEF_DATE_END_REAL = "DATETIME";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_CODE + " " + DEF_CODE + ", "
				+ COL_DATE_CREATED + " " + DEF_DATE_CREATED + ", "
				+ COL_ID_OBJECTIVE + " " + DEF_ID_OBJECTIVE + ", "
				+ COL_TYPE + " " + DEF_TYPE + ", "
				+ COL_STATUS + " " + DEF_STATUS + ", "
				+ COL_TITLE + " " + DEF_TITLE + ", "
				+ COL_DESCRIPTION + " " + DEF_DESCRIPTION + ", "
				+ COL_DIFFICULTY_ESTIMATED + " " + DEF_DIFFICULTY_ESTIMATED + ", "
				+ COL_DIFFICULTY_REAL + " " + DEF_DIFFICULTY_REAL + ", "
				+ COL_DURATION_ESTIMATED + " " + DEF_DURATION_ESTIMATED + ", "
				+ COL_DURATION_REAL + " " + DEF_DURATION_REAL + ", "
				+ COL_DATE_START_ESTIMATED + " " + DEF_DATE_START_ESTIMATED + ", "
				+ COL_DATE_END_ESTIMATED + " " + DEF_DATE_END_ESTIMATED + ", "
				+ COL_DATE_START_REAL + " " + DEF_DATE_START_REAL + ", "
				+ COL_DATE_END_REAL + " " + DEF_DATE_END_REAL + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
