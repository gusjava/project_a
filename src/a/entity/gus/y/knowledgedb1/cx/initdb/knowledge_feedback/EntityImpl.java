package a.entity.gus.y.knowledgedb1.cx.initdb.knowledge_feedback;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge_feedback";
	public static final String COL_ID = "id";
	public static final String COL_ID_KNOWLEDGE = "id_knowledge";
	public static final String COL_DATE = "date";
	public static final String COL_VERDICT = "verdict";
	public static final String COL_CONTEXT = "context";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_ID_KNOWLEDGE = "BIGINT NOT NULL";
	public static final String DEF_DATE = "DATETIME NOT NULL";
	public static final String DEF_VERDICT = "VARCHAR(20) NOT NULL";
	public static final String DEF_CONTEXT = "TEXT";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID + " " + DEF_ID + ", "
				+ COL_ID_KNOWLEDGE + " " + DEF_ID_KNOWLEDGE + ", "
				+ COL_DATE + " " + DEF_DATE + ", "
				+ COL_VERDICT + " " + DEF_VERDICT + ", "
				+ COL_CONTEXT + " " + DEF_CONTEXT + ")";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
