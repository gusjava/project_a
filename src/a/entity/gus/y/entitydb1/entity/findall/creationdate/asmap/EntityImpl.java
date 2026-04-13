package a.entity.gus.y.entitydb1.entity.findall.creationdate.asmap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public static final String TABLE_NAME = "entity";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_CREATION_DATE = "creation_date";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT " + COL_ENTITY_NAME + ", " + COL_CREATION_DATE + " FROM " + TABLE_NAME;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		Map data = new HashMap();
		while (rs.next()) {
			String entityName = rs.getString(COL_ENTITY_NAME);
			data.put(entityName, rs.getTimestamp(COL_CREATION_DATE).toString());
		}
		st.close();
		return data;
	}
}