package a.entity.gus.y.roadmapdb1.task.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "task";
	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_DATE_CREATED = "date_created";
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
			transfer(data, rs, COL_CODE);
			transfer(data, rs, COL_DATE_CREATED);
			transfer(data, rs, COL_ID_OBJECTIVE);
			transfer(data, rs, COL_TYPE);
			transfer(data, rs, COL_STATUS);
			transfer(data, rs, COL_TITLE);
			transfer(data, rs, COL_DESCRIPTION);
			transfer(data, rs, COL_DIFFICULTY_ESTIMATED);
			transfer(data, rs, COL_DIFFICULTY_REAL);
			transfer(data, rs, COL_DURATION_ESTIMATED);
			transfer(data, rs, COL_DURATION_REAL);
			transfer(data, rs, COL_DATE_START_ESTIMATED);
			transfer(data, rs, COL_DATE_END_ESTIMATED);
			transfer(data, rs, COL_DATE_START_REAL);
			transfer(data, rs, COL_DATE_END_REAL);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
