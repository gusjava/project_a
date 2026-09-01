package a.entity.gus.y.knowledgedb1.spec_rule.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "spec_rule";
	public static final String COL_SPEC_ID = "spec_id";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long specId = (Long) o[1];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_SPEC_ID + "=?";
		executeUpdate(cx, sql, specId);
	}

	private void executeUpdate(Connection cx, String sql, Object param) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		st.executeUpdate();
		st.close();
	}
}