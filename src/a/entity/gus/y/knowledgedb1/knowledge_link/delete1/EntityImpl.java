package a.entity.gus.y.knowledgedb1.knowledge_link.delete1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge_link";
	public static final String COL_ID_LINKER = "id_linker";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idLinker = (Long) o[1];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID_LINKER + "=?";
		executeUpdate(cx, sql, idLinker);
	}

	private void executeUpdate(Connection cx, String sql, Object param) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		st.executeUpdate();
		st.close();
	}
}
