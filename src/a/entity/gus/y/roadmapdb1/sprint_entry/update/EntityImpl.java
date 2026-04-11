package a.entity.gus.y.roadmapdb1.sprint_entry.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "sprint_entry";
	public static final String COL_ID = "id";
	public static final String COL_DATE = "date";
	public static final String COL_ID_TASK = "id_task";
	public static final String COL_DESCRIPTION = "description";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COL_DATE + "=?, "
				+ COL_ID_TASK + "=?, "
				+ COL_DESCRIPTION + "=? "
				+ "WHERE " + COL_ID + "=?";

		executeUpdate(cx, sql,
				data.get(COL_DATE),
				data.get(COL_ID_TASK),
				data.get(COL_DESCRIPTION),
				data.get(COL_ID));
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
