package a.entity.gus.y.roadmapdb1.sprint_entry.findall;

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

	public static final String TABLE_NAME = "sprint_entry";
	public static final String COL_ID = "id";
	public static final String COL_ID_SPRINT = "id_sprint";
	public static final String COL_DATE = "date";
	public static final String COL_ID_TASK = "id_task";
	public static final String COL_DESCRIPTION = "description";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idSprint = (Long) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID_SPRINT + "=? ORDER BY " + COL_DATE + " DESC";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idSprint);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_ID_SPRINT);
			transfer(m, rs, COL_DATE);
			transfer(m, rs, COL_ID_TASK);
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
