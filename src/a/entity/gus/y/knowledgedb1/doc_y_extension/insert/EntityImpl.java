package a.entity.gus.y.knowledgedb1.doc_y_extension.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_extension";
	public static final String COL_UNIT_Y_ID = "unit_y_id";
	public static final String COL_NAME = "name";
	public static final String COL_KIND = "kind";
	public static final String COL_DIRECTION = "direction";
	public static final String COL_ENTRY_POINT = "entry_point";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_SPEC_ID = "spec_id";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_UNIT_Y_ID + ", " + COL_NAME + ", " + COL_KIND + ", "
				+ COL_DIRECTION + ", " + COL_ENTRY_POINT + ", " + COL_DESCRIPTION + ", " + COL_SPEC_ID
				+ ") VALUES (?,?,?,?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, data.get(COL_UNIT_Y_ID));
		st.setObject(2, data.get(COL_NAME));
		st.setObject(3, data.get(COL_KIND));
		st.setObject(4, data.get(COL_DIRECTION));
		st.setObject(5, data.get(COL_ENTRY_POINT));
		st.setObject(6, data.get(COL_DESCRIPTION));
		st.setObject(7, data.get(COL_SPEC_ID));
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}