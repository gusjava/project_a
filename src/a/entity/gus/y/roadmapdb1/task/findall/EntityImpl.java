package a.entity.gus.y.roadmapdb1.task.findall;

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
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_DATE_CREATED + " DESC";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_CODE);
			transfer(m, rs, COL_DATE_CREATED);
			transfer(m, rs, COL_ID_OBJECTIVE);
			transfer(m, rs, COL_TYPE);
			transfer(m, rs, COL_STATUS);
			transfer(m, rs, COL_TITLE);
			transfer(m, rs, COL_DESCRIPTION);
			transfer(m, rs, COL_DIFFICULTY_ESTIMATED);
			transfer(m, rs, COL_DIFFICULTY_REAL);
			transfer(m, rs, COL_DURATION_ESTIMATED);
			transfer(m, rs, COL_DURATION_REAL);
			transfer(m, rs, COL_DATE_START_ESTIMATED);
			transfer(m, rs, COL_DATE_END_ESTIMATED);
			transfer(m, rs, COL_DATE_START_REAL);
			transfer(m, rs, COL_DATE_END_REAL);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
