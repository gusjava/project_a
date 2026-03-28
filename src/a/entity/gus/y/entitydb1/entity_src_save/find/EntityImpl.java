package a.entity.gus.y.entitydb1.entity_src_save.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240717";}

	public static final String TABLE_NAME = "entity_src_save";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_SRC = "src";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		String fileName = (String) o[2];

		String sql = "SELECT " + COL_SRC + " FROM " + TABLE_NAME + " WHERE " +
		 COL_ENTITY_NAME + "=? AND " + COL_FILE_NAME + "=?";
		
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		st.setObject(2, fileName);
		ResultSet rs = st.executeQuery();

		String src = null;
		if (rs.next()) {
			src = rs.getString(COL_SRC);
		}
		st.close();
		return src;
	}
}
