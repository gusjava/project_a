package a.entity.gus.y.roadmapdb1.note_tag.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	public static final String TABLE_NAME = "note_tag";
	public static final String COL_ID_NOTE = "id_note";
	public static final String COL_TAG = "tag";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idNote = (Long) o[1];

		String sql = "SELECT " + COL_TAG + " FROM " + TABLE_NAME + " WHERE " + COL_ID_NOTE + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idNote);
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next())
			data.add(rs.getString(COL_TAG));
		st.close();
		return data;
	}
}
