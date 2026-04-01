package a.entity.gus06.y.filedb1.cx.initdb.hdd;

import a.framework.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251126";}
	
	public static final String TABLE_NAME = "hdd";

	public static final String COL_SERIAL = "serial";
	public static final String COL_NAME = "name";
	public static final String COL_BRAND = "brand";
	public static final String COL_TYPE = "type";
	public static final String COL_SIZE = "size";
	public static final String COL_CREATED = "created";

	public static final String DEF_SERIAL = "VARCHAR(10) PRIMARY KEY NOT NULL";
	public static final String DEF_NAME = "VARCHAR(100) NOT NULL";
	public static final String DEF_BRAND = "VARCHAR(100) NULL";
	public static final String DEF_TYPE = "VARCHAR(100) NULL";
	public static final String DEF_SIZE = "BIGINT NOT NULL";
	public static final String DEF_CREATED = "DATETIME NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_SERIAL+" "+DEF_SERIAL+", "
				+COL_NAME+" "+DEF_NAME+", "
				+COL_BRAND+" "+DEF_BRAND+", "
				+COL_TYPE+" "+DEF_TYPE+", "
				+COL_SIZE+" "+DEF_SIZE+", "
				+COL_CREATED+" "+DEF_CREATED+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}