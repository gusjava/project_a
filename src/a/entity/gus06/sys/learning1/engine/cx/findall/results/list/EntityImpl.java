package a.entity.gus06.sys.learning1.engine.cx.findall.results.list;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250709";}

	public static final String TABLE_NAME = "results";

	public static final String COL_ID = "id";
	public static final String COL_DATE = "date";
	public static final String COL_QUESTION = "question";
	public static final String COL_ANSWER_RIGHT = "answer_right";
	public static final String COL_ANSWER_USER = "answer_user";
	public static final String COL_SUCCESS = "success";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_DATE;
		
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List list = new ArrayList();
		while (rs.next())
		{
			Map m = new HashMap();
			
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_DATE);
			transfer(m, rs, COL_QUESTION);
			transfer(m, rs, COL_ANSWER_RIGHT);
			transfer(m, rs, COL_ANSWER_USER);
			transfer(m, rs, COL_SUCCESS);

			list.add(m);
		}
		st.close();
		return list;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
