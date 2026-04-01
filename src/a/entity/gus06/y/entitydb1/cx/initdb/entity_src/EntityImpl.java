package a.entity.gus06.y.entitydb1.cx.initdb.entity_src;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251111";}
	
	public static final String TABLE_NAME = "entity_src";

	public static final String COL_ID = "id";
	public static final String COL_DATE = "date";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_SRC = "src";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_DATE = "DATETIME NOT NULL";
	public static final String DEF_ENTITY_NAME = "VARCHAR(200) NOT NULL";
	public static final String DEF_FILE_NAME = "VARCHAR(50) NOT NULL";
	public static final String DEF_SRC = "TEXT NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_DATE+" "+DEF_DATE+", "
				+COL_ENTITY_NAME+" "+DEF_ENTITY_NAME+", "
				+COL_FILE_NAME+" "+DEF_FILE_NAME+", "
				+COL_SRC+" "+DEF_SRC+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
