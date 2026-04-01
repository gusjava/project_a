package a.entity.gus06.y.entitydb1.cx.initdb.entity_compile_err;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251111";}
	
	public static final String TABLE_NAME = "entity_compile_err";

	public static final String COL_ID = "id";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_DATE = "date";
	public static final String COL_LINE = "line";
	public static final String COL_LINE_NB = "line_nb";
	public static final String COL_LINE_POS = "line_pos";
	public static final String COL_TYPE = "type";
	public static final String COL_DESCRIPTION = "description";

	public static final String DEF_ID = "BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_ENTITY_NAME = "VARCHAR(200) NOT NULL";
	public static final String DEF_FILE_NAME = "VARCHAR(50) NOT NULL";
	public static final String DEF_DATE = "DATETIME NOT NULL";
	public static final String DEF_LINE = "VARCHAR(200) NOT NULL";
	public static final String DEF_LINE_NB = "INT NOT NULL";
	public static final String DEF_LINE_POS = "INT NOT NULL";
	public static final String DEF_TYPE = "VARCHAR(50) NOT NULL";
	public static final String DEF_DESCRIPTION = "VARCHAR(200) NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_ENTITY_NAME+" "+DEF_ENTITY_NAME+", "
				+COL_FILE_NAME+" "+DEF_FILE_NAME+", "
				+COL_DATE+" "+DEF_DATE+", "
				+COL_LINE+" "+DEF_LINE+", "
				+COL_LINE_NB+" "+DEF_LINE_NB+", "
				+COL_LINE_POS+" "+DEF_LINE_POS+", "
				+COL_TYPE+" "+DEF_TYPE+", "
				+COL_DESCRIPTION+" "+DEF_DESCRIPTION+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}