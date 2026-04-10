package a.entity.gus.y.knowledgedb1.cx.initdb.knowledge;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge";
	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_DATE_CREATED = "DATETIME NOT NULL";
	public static final String DEF_DATE_UPDATED = "DATETIME NULL";
	public static final String DEF_ACTION = "VARCHAR(20) NOT NULL";
	public static final String DEF_OBJECT = "VARCHAR(200) NOT NULL";
	public static final String DEF_DESCRIPTION = "TEXT";
	public static final String DEF_STATE = "VARCHAR(20) NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_DATE_CREATED + " " + DEF_DATE_CREATED + ", "
				+ COL_DATE_UPDATED + " " + DEF_DATE_UPDATED + ", "
				+ COL_ACTION + " " + DEF_ACTION + ", "
				+ COL_OBJECT + " " + DEF_OBJECT + ", "
				+ COL_DESCRIPTION + " " + DEF_DESCRIPTION + ", "
				+ COL_STATE + " " + DEF_STATE + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
