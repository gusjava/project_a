package a.entity.gus.y.filedb1.cx.initdb.hdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240125";}

	public static final String TABLENAME = "hdd";

	public static final String COL_SERIAL =	"serial";
	public static final String COL_NAME = "name";
	public static final String COL_BRAND = "brand";
	public static final String COL_SIZE = "size";
	public static final String COL_CREATED = "created";

	public static final String DEF_SERIAL = 	"VARCHAR(32) PRIMARY KEY NOT NULL";
	public static final String DEF_NAME = "VARCHAR(100) NOT NULL";
	public static final String DEF_BRAND = "VARCHAR(100) NOT NULL";
	public static final String DEF_SIZE = "BIGINT(20) NOT NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_SERIAL+" "+DEF_SERIAL+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_BRAND+" "+DEF_BRAND+", "
				+COL_SIZE+" "+DEF_SIZE+", "
				+COL_CREATED+" "+DEF_CREATED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
