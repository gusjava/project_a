package a.entity.gus.y.entitydb1.entity_link.count1byname;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260502";}

	public static final String TABLE_NAME = "entity_link";

	public static final String COL_ENTITY_NAME = "entity_name";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT " + COL_ENTITY_NAME + ", COUNT(*) AS nb FROM " + TABLE_NAME
				+ " GROUP BY " + COL_ENTITY_NAME;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		Map data = new HashMap();
		while (rs.next()) {
			String entityName = (String) rs.getObject(COL_ENTITY_NAME);
			int nb = rs.getInt("nb");
			data.put(entityName, nb);
		}
		st.close();
		return data;
	}
}
