package a.entity.gus.y.entitydb1.cx.initdb.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20231206";}

	public static final String TABLE_NAME = "entity";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALL_NB = "call_nb";
	public static final String COL_FILE_NB = "file_nb";

	public static final String DEF_ENTITY_NAME = "VARCHAR(200) PRIMARY KEY NOT NULL";
	public static final String DEF_FEATURES = "VARCHAR(11) NOT NULL";
	public static final String DEF_CREATION_DATE = "DATETIME NOT NULL";
	public static final String DEF_LENGTH = "INT NOT NULL";
	public static final String DEF_CALL_NB = "INT NOT NULL";
	public static final String DEF_FILE_NB = "INT NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_ENTITY_NAME+" "+DEF_ENTITY_NAME+", "
				+COL_FEATURES+" "+DEF_FEATURES+", "
				+COL_CREATION_DATE+" "+DEF_CREATION_DATE+", "
				+COL_LENGTH+" "+DEF_LENGTH+", "
				+COL_CALL_NB+" "+DEF_CALL_NB+", "
				+COL_FILE_NB+" "+DEF_FILE_NB+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
