package a.entity.gus.y.knowledgedb1.doc_y_index.find;

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
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_index";
	public static final String COL_DOC_Y_ID = "doc_y_id";
	public static final String COL_MEMBER_KEY = "member_key";
	public static final String COL_DESCRIPTION = "description";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long docYId = (Long) o[1];

		String sql = "SELECT " + COL_MEMBER_KEY + ", " + COL_DESCRIPTION
				+ " FROM " + TABLE_NAME + " WHERE " + COL_DOC_Y_ID + "=?";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, docYId);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_MEMBER_KEY);
			transfer(m, rs, COL_DESCRIPTION);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}