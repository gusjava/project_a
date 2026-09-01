package a.entity.gus.y.knowledgedb1.doc_x_spec.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_x_spec";
	public static final String COL_DOC_X_ID = "doc_x_id";
	public static final String COL_SPEC_ID = "spec_id";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long docXId = (Long) o[1];
		Long specId = (Long) o[2];

		String sql = "INSERT INTO " + TABLE_NAME + " (" + COL_DOC_X_ID + ", " + COL_SPEC_ID + ") VALUES (?,?)";
		executeUpdate(cx, sql, docXId, specId);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}