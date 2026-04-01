package a.entity.gus06.sys.taskmanager1.engine.cx.initdb.comment;

import a.framework.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250819";}

	public static final String TABLE_NAME = "comment";

	public static final String COL_ID = "id";
	public static final String COL_COMMENT = "comment";
	public static final String COL_TASK_ID = "task_id";
	public static final String COL_DATE = "date";

	public static final String DEF_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String DEF_COMMENT = "TEXT NOT NULL";
	public static final String DEF_TASK_ID = "INTEGER NOT NULL";
	public static final String DEF_DATE = "TEXT NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
		
			+ COL_ID + " " + DEF_ID + ", "
			+ COL_COMMENT + " " + DEF_COMMENT + ", "
			+ COL_TASK_ID + " " + DEF_TASK_ID + ", "
			+ COL_DATE + " " + DEF_DATE + ", "
			+ "FOREIGN KEY(" + COL_TASK_ID + ") REFERENCES task(id)"
			+ ")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}