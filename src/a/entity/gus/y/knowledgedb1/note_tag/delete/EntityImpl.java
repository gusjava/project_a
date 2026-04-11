package a.entity.gus.y.knowledgedb1.note_tag.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "note_tag";
	public static final String COL_ID_NOTE = "id_note";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idNote = (Long) o[1];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID_NOTE + "=?";
		executeUpdate(cx, sql, idNote);
	}

	private void executeUpdate(Connection cx, String sql, Object param) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		st.executeUpdate();
		st.close();
	}
}
