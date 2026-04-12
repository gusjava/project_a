package a.entity.gus.y.entitydb1.entity_src_save.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240717";}

	public static final String TABLE_NAME = "entity_src_save";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT " + COL_ENTITY_NAME + ", " + COL_FILE_NAME +  " FROM " + TABLE_NAME + " ORDER BY " + COL_ENTITY_NAME;

		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next()) {
			String entityName = rs.getString(COL_ENTITY_NAME);
			String fileName = rs.getString(COL_FILE_NAME);
			
			if (!data.containsKey(entityName))
				data.put(entityName, new ArrayList());
			((List) data.get(entityName)).add(fileName);
		}
		st.close();
		return data;
	}
}
