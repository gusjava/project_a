package a.entity.gus.y.roadmapdb1.objective_tag.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "objective_tag";
	public static final String COL_ID_OBJECTIVE = "id_objective";
	public static final String COL_TAG = "tag";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idObjective = (Long) o[1];
		String tag = (String) o[2];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID_OBJECTIVE + "=? AND " + COL_TAG + "=?";
		executeUpdate(cx, sql, idObjective, tag);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
