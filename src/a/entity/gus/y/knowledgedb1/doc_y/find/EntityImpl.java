package a.entity.gus.y.knowledgedb1.doc_y.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long id = (Long) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, id);
		ResultSet rs = st.executeQuery();

		Map data = null;
		if (rs.next()) {
			data = new HashMap();
			transfer(data, rs, COL_ID);
			transfer(data, rs, COL_DATE_CREATED);
			transfer(data, rs, COL_DATE_UPDATED);
			transfer(data, rs, COL_NAME);
			transfer(data, rs, COL_SIGN);
			transfer(data, rs, COL_DESCRIPTION);
			transfer(data, rs, COL_STATE);
			transfer(data, rs, COL_DIFFICULTY_LEVEL);
			transfer(data, rs, COL_ISSUE_LEVEL);
			transfer(data, rs, COL_COMMENT);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
