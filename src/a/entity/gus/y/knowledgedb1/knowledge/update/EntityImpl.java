package a.entity.gus.y.knowledgedb1.knowledge.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge";
	public static final String COL_ID = "id";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object id = data.get(COL_ID);
		Object action = data.get(COL_ACTION);
		Object object = data.get(COL_OBJECT);
		Object description = data.get(COL_DESCRIPTION);
		Object state = data.get(COL_STATE);

		String sql = "UPDATE " + TABLE_NAME + " SET "
				+ COL_DATE_UPDATED + "=?, "
				+ COL_ACTION + "=?, "
				+ COL_OBJECT + "=?, "
				+ COL_DESCRIPTION + "=?, "
				+ COL_STATE + "=? "
				+ "WHERE " + COL_ID + "=?";

		executeUpdate(cx, sql, new Date(), action, object, description, state, id);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
