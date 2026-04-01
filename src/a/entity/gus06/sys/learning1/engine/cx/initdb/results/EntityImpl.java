package a.entity.gus06.sys.learning1.engine.cx.initdb.results;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250707";}

	public static final String TABLE_NAME = "results";

	public static final String COL_ID = "id";
	public static final String COL_DATE = "date";
	public static final String COL_QUESTION = "question";
	public static final String COL_ANSWER_RIGHT = "answer_right";
	public static final String COL_ANSWER_USER = "answer_user";
	public static final String COL_SUCCESS = "success";

	public static final String DEF_ID = "BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL";
	public static final String DEF_DATE = "DATETIME NOT NULL";
	public static final String DEF_QUESTION = "VARCHAR(20) NOT NULL";
	public static final String DEF_ANSWER_RIGHT = "VARCHAR(20) NOT NULL";
	public static final String DEF_ANSWER_USER = "VARCHAR(20) NOT NULL";
	public static final String DEF_SUCCESS = "TINYINT NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_DATE+" "+DEF_DATE+", "
				+COL_QUESTION+" "+DEF_QUESTION+", "
				+COL_ANSWER_RIGHT+" "+DEF_ANSWER_RIGHT+", "
				+COL_ANSWER_USER+" "+DEF_ANSWER_USER+", "
				+COL_SUCCESS+" "+DEF_SUCCESS+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}