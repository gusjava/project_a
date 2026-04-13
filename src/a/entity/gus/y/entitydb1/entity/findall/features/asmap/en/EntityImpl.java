package a.entity.gus.y.entitydb1.entity.findall.features.asmap.en;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public static final String TABLE_NAME = "entity";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FEATURES = "features";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String suffix = (String) o[1];

		String sql = "SELECT " + COL_ENTITY_NAME + ", " + COL_FEATURES + " FROM " + TABLE_NAME + " WHERE " + COL_FEATURES + " LIKE ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, "%" + suffix);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next()) {
			String entityName = rs.getString(COL_ENTITY_NAME);
			String features = rs.getString(COL_FEATURES);
			data.put(entityName, features);
		}
		st.close();
		return data;
	}
}
