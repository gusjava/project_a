package a.entity.gus.y.knowledgedb1.doc_y_extension.find1;

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

	public static final String TABLE_NAME = "doc_y_extension";
	public static final String COL_ID = "id";
	public static final String COL_UNIT_Y_ID = "unit_y_id";
	public static final String COL_NAME = "name";
	public static final String COL_KIND = "kind";
	public static final String COL_DIRECTION = "direction";
	public static final String COL_ENTRY_POINT = "entry_point";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_SPEC_ID = "spec_id";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long unitYId = (Long) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_UNIT_Y_ID + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, unitYId);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_UNIT_Y_ID);
			transfer(m, rs, COL_NAME);
			transfer(m, rs, COL_KIND);
			transfer(m, rs, COL_DIRECTION);
			transfer(m, rs, COL_ENTRY_POINT);
			transfer(m, rs, COL_DESCRIPTION);
			transfer(m, rs, COL_SPEC_ID);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}