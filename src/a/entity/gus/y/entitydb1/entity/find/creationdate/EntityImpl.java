package a.entity.gus.y.entitydb1.entity.find.creationdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public static final String TABLE_NAME = "entity";
	public static final String COL_NAME = "entity_name";
	public static final String COL_CREATION_DATE = "creation_date";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String name = (String) o[1];

		String sql = "SELECT " + COL_NAME + ", " + COL_CREATION_DATE + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();

		Timestamp creationDate = null;
		if (rs.next()) {
			creationDate = rs.getTimestamp(COL_CREATION_DATE);
		}
		st.close();
		return creationDate;
	}
}
