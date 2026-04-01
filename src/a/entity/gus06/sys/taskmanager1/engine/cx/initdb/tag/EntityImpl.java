package a.entity.gus06.sys.taskmanager1.engine.cx.initdb.tag;

import a.framework.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250819";}

	public static final String TABLE_NAME = "tag";

	public static final String COL_ID = "id";
	public static final String COL_LABEL = "label";
	public static final String COL_TASK_ID = "task_id";

	public static final String DEF_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String DEF_LABEL = "TEXT NOT NULL";
	public static final String DEF_TASK_ID = "INTEGER NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
		
			+ COL_ID + " " + DEF_ID + ", "
			+ COL_LABEL + " " + DEF_LABEL + ", "
			+ COL_TASK_ID + " " + DEF_TASK_ID + ", "
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