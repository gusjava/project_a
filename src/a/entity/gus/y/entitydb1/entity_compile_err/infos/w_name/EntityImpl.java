package a.entity.gus.y.entitydb1.entity_compile_err.infos.w_name;

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
	public String creationDate() {return "20240112";}

	public static final String TABLE_NAME = "entity_compile_err";

	public static final String COL_ID = "id";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_DATE = "date";
	public static final String COL_LINE = "line";
	public static final String COL_LINE_NB = "line_nb";
	public static final String COL_LINE_POS = "line_pos";
	public static final String COL_TYPE = "type";
	public static final String COL_DESCRIPTION = "description";

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " 
		+ COL_ENTITY_NAME + "=? ORDER BY " 
		+ COL_FILE_NAME + "," + COL_LINE_NB + "," + COL_LINE_POS;

		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ENTITY_NAME);
			transfer(m, rs, COL_FILE_NAME);
			transfer(m, rs, COL_DATE);
			transfer(m, rs, COL_LINE);
			transfer(m, rs, COL_LINE_NB);
			transfer(m, rs, COL_LINE_POS);
			transfer(m, rs, COL_TYPE);
			transfer(m, rs, COL_DESCRIPTION);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
