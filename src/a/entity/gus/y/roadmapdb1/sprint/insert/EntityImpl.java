package a.entity.gus.y.roadmapdb1.sprint.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "sprint";
	public static final String COL_NAME = "name";
	public static final String COL_DATE_START = "date_start";
	public static final String COL_DATE_END = "date_end";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_NAME + ", " + COL_DATE_START + ", " + COL_DATE_END
				+ ") VALUES (?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, data.get(COL_NAME));
		st.setObject(2, data.get(COL_DATE_START));
		st.setObject(3, data.get(COL_DATE_END));
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
