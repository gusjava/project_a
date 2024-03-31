package a.entity.gus.y.entitydb1.entity_compile_err.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
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

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object entityName = data.get(COL_ENTITY_NAME);
		Object fileName = data.get(COL_FILE_NAME);
		Object date = data.get(COL_DATE);
		Object line = data.get(COL_LINE);
		Object lineNb = data.get(COL_LINE_NB);
		Object linePos = data.get(COL_LINE_POS);
		Object type = data.get(COL_TYPE);
		Object description = data.get(COL_DESCRIPTION);

		String sql = "INSERT INTO " + TABLE_NAME + " ("
		 + COL_ENTITY_NAME + ","
		 + COL_FILE_NAME + ","
		 + COL_DATE + ","
		 + COL_LINE + ","
		 + COL_LINE_NB + ","
		 + COL_LINE_POS + ","
		 + COL_TYPE + ","
		 + COL_DESCRIPTION + ") VALUES (?,?,?,?,?,?,?,?)";

		executeUpdate(cx, sql, entityName, fileName, date, line, lineNb, linePos, type, description);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
