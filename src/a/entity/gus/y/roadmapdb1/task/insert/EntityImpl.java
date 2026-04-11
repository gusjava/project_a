package a.entity.gus.y.roadmapdb1.task.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "task";
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
		Map data = (Map) o[1];

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_CODE + ", " + COL_DATE_CREATED + ", " + COL_ID_OBJECTIVE + ", "
				+ COL_TYPE + ", " + COL_STATUS + ", " + COL_TITLE + ", " + COL_DESCRIPTION + ", "
				+ COL_DIFFICULTY_ESTIMATED + ", " + COL_DIFFICULTY_REAL + ", "
				+ COL_DURATION_ESTIMATED + ", " + COL_DURATION_REAL + ", "
				+ COL_DATE_START_ESTIMATED + ", " + COL_DATE_END_ESTIMATED + ", "
				+ COL_DATE_START_REAL + ", " + COL_DATE_END_REAL
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, data.get(COL_CODE));
		st.setObject(2, new Date());
		st.setObject(3, data.get(COL_ID_OBJECTIVE));
		st.setObject(4, data.get(COL_TYPE));
		st.setObject(5, data.get(COL_STATUS));
		st.setObject(6, data.get(COL_TITLE));
		st.setObject(7, data.get(COL_DESCRIPTION));
		st.setObject(8, data.get(COL_DIFFICULTY_ESTIMATED));
		st.setObject(9, data.get(COL_DIFFICULTY_REAL));
		st.setObject(10, data.get(COL_DURATION_ESTIMATED));
		st.setObject(11, data.get(COL_DURATION_REAL));
		st.setObject(12, data.get(COL_DATE_START_ESTIMATED));
		st.setObject(13, data.get(COL_DATE_END_ESTIMATED));
		st.setObject(14, data.get(COL_DATE_START_REAL));
		st.setObject(15, data.get(COL_DATE_END_REAL));
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
