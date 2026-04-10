package a.entity.gus.y.knowledgedb1.doc_z_tag.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "doc_z_tag";
	public static final String COL_ID = "id";
	public static final String COL_TAG = "tag";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long id = (Long) o[1];
		String tag = (String) o[2];

		String sql = "INSERT INTO " + TABLE_NAME + " (" + COL_ID + ", " + COL_TAG + ") VALUES (?,?)";
		executeUpdate(cx, sql, id, tag);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
