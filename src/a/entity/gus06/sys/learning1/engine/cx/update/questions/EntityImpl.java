package a.entity.gus06.sys.learning1.engine.cx.update.questions;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250712";}

	public static final String TABLE_NAME = "questions";

	public static final String COL_CODE = "code";
	public static final String COL_STATUS = "status";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_NB_TOTAL = "nb_total";
	public static final String COL_NB_TOTAL_SUCCESS = "nb_total_success";
	public static final String COL_NB_TOTAL_FAIL = "nb_total_fail";
	public static final String COL_NB_LATEST_SUCCESS = "nb_latest_success";
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object code = data.get(COL_CODE);
		Object status = data.get(COL_STATUS);
		Object nbTotal = data.get(COL_NB_TOTAL);
		Object nbTotalSuccess = data.get(COL_NB_TOTAL_SUCCESS);
		Object nbTotalFail = data.get(COL_NB_TOTAL_FAIL);
		Object nbLatestSuccess = data.get(COL_NB_LATEST_SUCCESS);

		String sql = "UPDATE " + TABLE_NAME + " SET " 
		+ COL_STATUS + "=?, " 
		+ COL_NB_TOTAL + "=?, "
		+ COL_NB_TOTAL_SUCCESS + "=?, " 
		+ COL_NB_TOTAL_FAIL + "=?, " 
		+ COL_NB_LATEST_SUCCESS + "=? WHERE " + COL_CODE + "=?";

		executeUpdate(cx, sql, status, nbTotal, nbTotalSuccess, nbTotalFail, nbLatestSuccess, code);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}