package a.entity.gus.y.filedb1.cx.initdb.root;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "root";

	public static final String COL_ID = "id";
	public static final String COL_SERIAL =	"serial";
	public static final String COL_NAME = "name";
	public static final String COL_LOCATION = "location";
	public static final String COL_CREATED = "created";
	public static final String COL_UPDATED = "updated";

	public static final String DEF_ID = "BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT";
	public static final String DEF_SERIAL = "VARCHAR(32)";
	public static final String DEF_NAME = "VARCHAR(100) NOT NULL";
	public static final String DEF_LOCATION = "VARCHAR(2000) NOT NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	public static final String DEF_UPDATED = "DATETIME NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_ID+" "+DEF_ID+", "
				+COL_SERIAL+" "+DEF_SERIAL+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_LOCATION+" "+DEF_LOCATION+", "
				+COL_CREATED+" "+DEF_CREATED+", "
				+COL_UPDATED+" "+DEF_UPDATED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
