package a.entity.gus.y.roadmapdb1.note.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "note";
	public static final String COL_ID = "id";
	public static final String COL_TITLE = "title";
	public static final String COL_CONTENT = "content";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COL_TITLE + "=?, "
				+ COL_CONTENT + "=? "
				+ "WHERE " + COL_ID + "=?";

		executeUpdate(cx, sql,
				data.get(COL_TITLE),
				data.get(COL_CONTENT),
				data.get(COL_ID));
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
