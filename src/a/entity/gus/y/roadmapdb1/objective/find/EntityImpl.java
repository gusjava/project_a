package a.entity.gus.y.roadmapdb1.objective.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "objective";
	public static final String COL_ID = "id";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATUS = "status";

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
			transfer(data, rs, COL_TITLE);
			transfer(data, rs, COL_DESCRIPTION);
			transfer(data, rs, COL_STATUS);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
