package a.entity.gus.y.entitydb1.entity.find.imports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	public static final String TABLE_NAME = "entity_import";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_ENTITY_IMPORT = "entity_import";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String name = (String) o[1];

		String sql = "SELECT " + COL_ENTITY_IMPORT + " FROM " + TABLE_NAME + " WHERE " + COL_ENTITY_NAME + " = ? ORDER BY " + COL_ENTITY_IMPORT;
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) data.add(rs.getString(COL_ENTITY_IMPORT));
		st.close();
		return data;
	}
}