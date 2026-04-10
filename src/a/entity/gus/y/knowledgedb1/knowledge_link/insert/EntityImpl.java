package a.entity.gus.y.knowledgedb1.knowledge_link.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge_link";
	public static final String COL_ID_LINKER = "id_linker";
	public static final String COL_ID_LINKED = "id_linked";
	public static final String COL_TYPE = "type";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idLinker = (Long) o[1];
		Long idLinked = (Long) o[2];
		String type = (String) o[3];

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_ID_LINKER + ", " + COL_ID_LINKED + ", " + COL_TYPE + ") VALUES (?,?,?)";
		executeUpdate(cx, sql, idLinker, idLinked, type);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
