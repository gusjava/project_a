package a.entity.gus06.sys.learning1.engine.cx.initdb.questions;

import a.framework.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250707";}

	public static final String TABLE_NAME = "questions";

	public static final String COL_CODE = "code";
	public static final String COL_STATUS = "status";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_NB_TOTAL = "nb_total";
	public static final String COL_NB_TOTAL_SUCCESS = "nb_total_success";
	public static final String COL_NB_TOTAL_FAIL = "nb_total_fail";
	public static final String COL_NB_LATEST_SUCCESS = "nb_latest_success";

	public static final String DEF_CODE = "VARCHAR(20) PRIMARY KEY NOT NULL";
	public static final String DEF_STATUS = "VARCHAR(20) NOT NULL";
	public static final String DEF_DATE_CREATED = "DATETIME NOT NULL";
	public static final String DEF_NB_TOTAL = "INT NOT NULL";
	public static final String DEF_NB_TOTAL_SUCCESS = "INT NOT NULL";
	public static final String DEF_NB_TOTAL_FAIL = "INT NOT NULL";
	public static final String DEF_NB_LATEST_SUCCESS = "INT NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLE_NAME+" ("
				+COL_CODE+" "+DEF_CODE+", "
				+COL_STATUS+" "+DEF_STATUS+", "
				+COL_DATE_CREATED+" "+DEF_DATE_CREATED+", "
				+COL_NB_TOTAL+" "+DEF_NB_TOTAL+", "
				+COL_NB_TOTAL_SUCCESS+" "+DEF_NB_TOTAL_SUCCESS+", "
				+COL_NB_TOTAL_FAIL+" "+DEF_NB_TOTAL_FAIL+", "
				+COL_NB_LATEST_SUCCESS+" "+DEF_NB_LATEST_SUCCESS+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException
	{
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}