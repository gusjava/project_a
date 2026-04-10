package a.entity.gus.y.knowledgedb1.doc_y.findall;

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

	public static final String TABLE_NAME = "doc_y";
	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_NAME = "name";
	public static final String COL_SIGN = "sign";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_DIFFICULTY_LEVEL = "difficulty_level";
	public static final String COL_ISSUE_LEVEL = "issue_level";
	public static final String COL_COMMENT = "comment";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_NAME;
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_DATE_CREATED);
			transfer(m, rs, COL_DATE_UPDATED);
			transfer(m, rs, COL_NAME);
			transfer(m, rs, COL_SIGN);
			transfer(m, rs, COL_DESCRIPTION);
			transfer(m, rs, COL_STATE);
			transfer(m, rs, COL_DIFFICULTY_LEVEL);
			transfer(m, rs, COL_ISSUE_LEVEL);
			transfer(m, rs, COL_COMMENT);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
