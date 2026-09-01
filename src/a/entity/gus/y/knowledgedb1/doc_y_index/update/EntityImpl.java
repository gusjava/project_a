package a.entity.gus.y.knowledgedb1.doc_y_index.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_index";
	public static final String COL_DOC_Y_ID = "doc_y_id";
	public static final String COL_MEMBER_KEY = "member_key";
	public static final String COL_DESCRIPTION = "description";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long docYId = (Long) o[1];
		String memberKey = (String) o[2];
		String description = (String) o[3];

		String sql = "UPDATE " + TABLE_NAME + " SET " + COL_DESCRIPTION + "=? "
				+ "WHERE " + COL_DOC_Y_ID + "=? AND " + COL_MEMBER_KEY + "=?";

		executeUpdate(cx, sql, description, docYId, memberKey);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}