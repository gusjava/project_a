package a.entity.gus06.sys.taskmanager1.engine.cx.initdb.task;

import a.framework.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250819";}

	public static final String TABLE_NAME = "task";

	public static final String COL_ID = "id";
	public static final String COL_TITLE = "title";
	public static final String COL_CONTENT = "content";
	public static final String COL_STATUS = "status";
	public static final String COL_LEVEL = "level";
	public static final String COL_PRIORITY = "priority";
	
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_DATE_STARTED = "date_started";
	public static final String COL_DATE_COMPLETED = "date_completed";
	public static final String COL_DATE_CANCELLED = "date_cancelled";
	

	public static final String DEF_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final String DEF_TITLE = "TEXT NOT NULL";
	public static final String DEF_CONTENT = "TEXT NOT NULL";
	public static final String DEF_STATUS = "TEXT NOT NULL";
	public static final String DEF_LEVEL = "TEXT";
	public static final String DEF_PRIORITY = "INTEGER";
	
	public static final String DEF_DATE_CREATED = "TEXT NOT NULL";
	public static final String DEF_DATE_UPDATED = "TEXT";
	public static final String DEF_DATE_STARTED = "TEXT";
	public static final String DEF_DATE_COMPLETED = "TEXT";
	public static final String DEF_DATE_CANCELLED = "TEXT";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
		
				+COL_ID+" "+DEF_ID+", "
				+COL_TITLE+" "+DEF_TITLE+", "
				+COL_CONTENT+" "+DEF_CONTENT+", "
				+COL_STATUS+" "+DEF_STATUS+", "
				+COL_LEVEL+" "+DEF_LEVEL+", "
				+COL_PRIORITY+" "+DEF_PRIORITY+", "
				
				+COL_DATE_CREATED+" "+DEF_DATE_CREATED+", "
				+COL_DATE_UPDATED+" "+DEF_DATE_UPDATED+", "
				+COL_DATE_STARTED+" "+DEF_DATE_STARTED+", "
				+COL_DATE_COMPLETED+" "+DEF_DATE_COMPLETED+", "
				+COL_DATE_CANCELLED+" "+DEF_DATE_CANCELLED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}