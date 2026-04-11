package a.entity.gus.y.roadmapdb1.objective.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "objective";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATUS = "status";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object title = data.get(COL_TITLE);
		Object description = data.get(COL_DESCRIPTION);
		Object status = data.get(COL_STATUS);

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_DATE_CREATED + ", " + COL_TITLE + ", " + COL_DESCRIPTION + ", " + COL_STATUS
				+ ") VALUES (?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, new Date());
		st.setObject(2, title);
		st.setObject(3, description);
		st.setObject(4, status);
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
