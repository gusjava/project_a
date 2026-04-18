package a.entity.gus.y.knowledgedb1.knowledge.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge";
	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_CODE = "code";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ID;
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_DATE_CREATED);
			transfer(m, rs, COL_DATE_UPDATED);
			transfer(m, rs, COL_CODE);
			transfer(m, rs, COL_ACTION);
			transfer(m, rs, COL_OBJECT);
			transfer(m, rs, COL_DESCRIPTION);
			transfer(m, rs, COL_STATE);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
