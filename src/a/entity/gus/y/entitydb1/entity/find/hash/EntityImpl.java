package a.entity.gus.y.entitydb1.entity.find.hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}

	public static final String TABLE_NAME = "entity";
	public static final String COL_NAME = "entity_name";
	public static final String COL_HASH = "hash";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String name = (String) o[1];

		String sql = "SELECT " + COL_NAME + ", " + COL_HASH + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();

		String hash = null;
		if (rs.next()) {
			hash = rs.getString(COL_HASH);
		}
		st.close();
		return hash;
	}
}
