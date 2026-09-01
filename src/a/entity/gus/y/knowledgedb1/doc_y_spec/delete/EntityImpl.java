package a.entity.gus.y.knowledgedb1.doc_y_spec.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_spec";
	public static final String COL_DOC_Y_ID = "doc_y_id";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long docYId = (Long) o[1];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_DOC_Y_ID + "=?";
		executeUpdate(cx, sql, docYId);
	}

	private void executeUpdate(Connection cx, String sql, Object param) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		st.executeUpdate();
		st.close();
	}
}