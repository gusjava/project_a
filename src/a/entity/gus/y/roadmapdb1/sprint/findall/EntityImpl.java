package a.entity.gus.y.roadmapdb1.sprint.findall;

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
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "sprint";
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_DATE_START = "date_start";
	public static final String COL_DATE_END = "date_end";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_DATE_START + " DESC";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_NAME);
			transfer(m, rs, COL_DATE_START);
			transfer(m, rs, COL_DATE_END);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
