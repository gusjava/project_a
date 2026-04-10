package a.entity.gus.y.knowledgedb1.cx.initdb.knowledge_link;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge_link";
	public static final String COL_ID_LINKER = "id_linker";
	public static final String COL_ID_LINKED = "id_linked";
	public static final String COL_TYPE = "type";

	public static final String DEF_ID_LINKER = "BIGINT NOT NULL";
	public static final String DEF_ID_LINKED = "BIGINT NOT NULL";
	public static final String DEF_TYPE = "VARCHAR(20) NOT NULL";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID_LINKER + " " + DEF_ID_LINKER + ", "
				+ COL_ID_LINKED + " " + DEF_ID_LINKED + ", "
				+ COL_TYPE + " " + DEF_TYPE + ", "
				+ "PRIMARY KEY (" + COL_ID_LINKER + ", " + COL_ID_LINKED + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
