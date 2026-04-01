package a.entity.gus06.y.entitydb1.entity_compile_err.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251112";}

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

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME 
		+ " ORDER BY " + COL_ENTITY_NAME + "," + COL_FILE_NAME + ","
		+ COL_LINE_NB + "," + COL_LINE_POS;

		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next())
		{
			Map m = new HashMap();
			transfer(m, rs, COL_ENTITY_NAME);
			transfer(m, rs, COL_FILE_NAME);
			transfer(m, rs, COL_DATE);
			transfer(m, rs, COL_LINE);
			transfer(m, rs, COL_LINE_NB);
			transfer(m, rs, COL_LINE_POS);
			transfer(m, rs, COL_TYPE);
			transfer(m, rs, COL_DESCRIPTION);

			String name = (String) m.get(COL_ENTITY_NAME);

			if (!data.containsKey(name)) data.put(name, new ArrayList());
			((List) data.get(name)).add(m);
		}
		rs.close();
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
