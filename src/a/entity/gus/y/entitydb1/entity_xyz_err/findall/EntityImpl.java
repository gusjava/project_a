package a.entity.gus.y.entitydb1.entity_xyz_err.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240119";}
	
	public static final String TABLE_NAME = "entity_xyz_err";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_LINK = "link";
	public static final String COL_POS = "pos";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ENTITY_NAME;

		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ENTITY_NAME);
			transfer(m, rs, COL_LINK);
			transfer(m, rs, COL_POS);

			String name = (String) m.get(COL_ENTITY_NAME);

			if (!data.containsKey(name))
				data.put(name, new ArrayList());
			((List) data.get(name)).add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
