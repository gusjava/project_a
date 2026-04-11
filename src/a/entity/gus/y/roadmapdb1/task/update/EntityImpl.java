package a.entity.gus.y.roadmapdb1.task.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "task";
	public static final String COL_ID = "id";
	public static final String COL_ID_OBJECTIVE = "id_objective";
	public static final String COL_TYPE = "type";
	public static final String COL_STATUS = "status";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_DIFFICULTY_ESTIMATED = "difficulty_estimated";
	public static final String COL_DIFFICULTY_REAL = "difficulty_real";
	public static final String COL_DURATION_ESTIMATED = "duration_estimated";
	public static final String COL_DURATION_REAL = "duration_real";
	public static final String COL_DATE_START_ESTIMATED = "date_start_estimated";
	public static final String COL_DATE_END_ESTIMATED = "date_end_estimated";
	public static final String COL_DATE_START_REAL = "date_start_real";
	public static final String COL_DATE_END_REAL = "date_end_real";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COL_ID_OBJECTIVE + "=?, "
				+ COL_TYPE + "=?, "
				+ COL_STATUS + "=?, "
				+ COL_TITLE + "=?, "
				+ COL_DESCRIPTION + "=?, "
				+ COL_DIFFICULTY_ESTIMATED + "=?, "
				+ COL_DIFFICULTY_REAL + "=?, "
				+ COL_DURATION_ESTIMATED + "=?, "
				+ COL_DURATION_REAL + "=?, "
				+ COL_DATE_START_ESTIMATED + "=?, "
				+ COL_DATE_END_ESTIMATED + "=?, "
				+ COL_DATE_START_REAL + "=?, "
				+ COL_DATE_END_REAL + "=? "
				+ "WHERE " + COL_ID + "=?";

		executeUpdate(cx, sql,
				data.get(COL_ID_OBJECTIVE),
				data.get(COL_TYPE),
				data.get(COL_STATUS),
				data.get(COL_TITLE),
				data.get(COL_DESCRIPTION),
				data.get(COL_DIFFICULTY_ESTIMATED),
				data.get(COL_DIFFICULTY_REAL),
				data.get(COL_DURATION_ESTIMATED),
				data.get(COL_DURATION_REAL),
				data.get(COL_DATE_START_ESTIMATED),
				data.get(COL_DATE_END_ESTIMATED),
				data.get(COL_DATE_START_REAL),
				data.get(COL_DATE_END_REAL),
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
