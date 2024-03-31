package a.entity.gus.y.entitydb1.entity_compile_err.distinctnames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240112";}

	public static final String TABLE_NAME = "entity_compile_err";

	public static final String COL_ENTITY_NAME = "entity_name";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];

		String sql = "SELECT DISTINCT(" + COL_ENTITY_NAME + ") FROM " + TABLE_NAME;
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next())
			data.add(rs.getObject(1));

		st.close();
		return data;
	}
}
