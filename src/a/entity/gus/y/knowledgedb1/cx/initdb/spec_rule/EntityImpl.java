package a.entity.gus.y.knowledgedb1.cx.initdb.spec_rule;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "spec_rule";
	public static final String COL_SPEC_ID = "spec_id";
	public static final String COL_RULE_ID = "rule_id";

	public static final String DEF_SPEC_ID = "BIGINT NOT NULL";
	public static final String DEF_RULE_ID = "BIGINT NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_SPEC_ID + " " + DEF_SPEC_ID + ", "
				+ COL_RULE_ID + " " + DEF_RULE_ID + ", "
				+ "PRIMARY KEY (" + COL_SPEC_ID + ", " + COL_RULE_ID + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}