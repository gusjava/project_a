package a.entity.gus.y.knowledgedb1.cx.initdb.todo_tag;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "todo_tag";
	public static final String COL_ID_TODO = "id_todo";
	public static final String COL_TAG = "tag";

	public static final String DEF_ID_TODO = "BIGINT NOT NULL";
	public static final String DEF_TAG = "VARCHAR(50) NOT NULL";

	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "CREATE TABLE " + TABLE_NAME + " ("
				+ COL_ID_TODO + " " + DEF_ID_TODO + ", "
				+ COL_TAG + " " + DEF_TAG + ", "
				+ "PRIMARY KEY (" + COL_ID_TODO + ", " + COL_TAG + "))";
		execute(cx, sql);
	}

	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
