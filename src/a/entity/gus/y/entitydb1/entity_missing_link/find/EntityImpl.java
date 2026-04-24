package a.entity.gus.y.entitydb1.entity_missing_link.find;

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
	public String creationDate() {return "20240126";}

	public static final String TABLE_NAME = "entity_missing_link";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_MISSING_LINK = "missing_link";
	public static final String COL_POS = "pos";

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME
		 + " WHERE " + COL_ENTITY_NAME + "=? ORDER BY " + COL_MISSING_LINK;

		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ENTITY_NAME);
			transfer(m, rs, COL_MISSING_LINK);
			transfer(m, rs, COL_POS);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
