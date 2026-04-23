package a.entity.gus.y.entitydb1.entity.infosbyname.w_st;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	public static final String TABLE_NAME = "entity";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALL_NB = "call_nb";
	public static final String COL_FILE_NB = "file_nb";
	public static final String COL_HASH = "hash";

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		String prefix = (String) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ENTITY_NAME + " LIKE ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, prefix + "%");
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ENTITY_NAME);
			transfer(m, rs, COL_FEATURES);
			transfer(m, rs, COL_CREATION_DATE);
			transfer(m, rs, COL_LENGTH);
			transfer(m, rs, COL_CALL_NB);
			transfer(m, rs, COL_FILE_NB);
			transfer(m, rs, COL_HASH);

			String entityName = (String) m.get(COL_ENTITY_NAME);
			data.put(entityName, m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
