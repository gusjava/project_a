package a.entity.gus.y.knowledgedb1.todo.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "todo";
	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object id = data.get(COL_ID);
		Object code = data.get(COL_CODE);
		Object title = data.get(COL_TITLE);
		Object description = data.get(COL_DESCRIPTION);

		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COL_CODE + "=?, "
				+ COL_TITLE + "=?, "
				+ COL_DESCRIPTION + "=? "
				+ "WHERE " + COL_ID + "=?";

		executeUpdate(cx, sql, code, title, description, id);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
