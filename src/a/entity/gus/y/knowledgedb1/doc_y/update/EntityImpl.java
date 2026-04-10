package a.entity.gus.y.knowledgedb1.doc_y.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "doc_y";
	public static final String COL_ID = "id";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_NAME = "name";
	public static final String COL_SIGN = "sign";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_DIFFICULTY_LEVEL = "difficulty_level";
	public static final String COL_ISSUE_LEVEL = "issue_level";
	public static final String COL_COMMENT = "comment";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COL_DATE_UPDATED + "=?, " + COL_NAME + "=?, " + COL_SIGN + "=?, "
				+ COL_DESCRIPTION + "=?, " + COL_STATE + "=?, "
				+ COL_DIFFICULTY_LEVEL + "=?, " + COL_ISSUE_LEVEL + "=?, " + COL_COMMENT + "=? "
				+ "WHERE " + COL_ID + "=?";

		executeUpdate(cx, sql,
				new Date(), data.get(COL_NAME), data.get(COL_SIGN),
				data.get(COL_DESCRIPTION), data.get(COL_STATE),
				data.get(COL_DIFFICULTY_LEVEL), data.get(COL_ISSUE_LEVEL),
				data.get(COL_COMMENT), data.get(COL_ID));
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
