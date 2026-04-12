package a.entity.gus.y.entitydb1.entity.findall.names;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	public static final String TABLE_NAME = "entity";
	public static final String COL_ENTITY_NAME = "entity_name";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT " + COL_ENTITY_NAME + " FROM " + TABLE_NAME;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		List data = new ArrayList();
		while (rs.next()) {
			data.add(rs.getString(COL_ENTITY_NAME));
		}
		return data;
	}
}
