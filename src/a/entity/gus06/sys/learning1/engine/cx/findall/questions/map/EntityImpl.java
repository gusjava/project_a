package a.entity.gus06.sys.learning1.engine.cx.findall.questions.map;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250708";}

	public static final String TABLE_NAME = "questions";

	public static final String COL_CODE = "code";
	public static final String COL_STATUS = "status";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_NB_TOTAL = "nb_total";
	public static final String COL_NB_TOTAL_SUCCESS = "nb_total_success";
	public static final String COL_NB_TOTAL_FAIL = "nb_total_fail";
	public static final String COL_NB_LATEST_SUCCESS = "nb_latest_success";
	
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT * FROM " + TABLE_NAME;
		
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next())
		{
			Map m = new HashMap();
			
			transfer(m, rs, COL_CODE);
			transfer(m, rs, COL_STATUS);
			transfer(m, rs, COL_DATE_CREATED);
			transfer(m, rs, COL_NB_TOTAL);
			transfer(m, rs, COL_NB_TOTAL_SUCCESS);
			transfer(m, rs, COL_NB_TOTAL_FAIL);
			transfer(m, rs, COL_NB_LATEST_SUCCESS);

			String code = (String) m.get(COL_CODE);

			if(data.containsKey(code))
				throw new Exception("Code found many times: "+code);
			data.put(code, m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}