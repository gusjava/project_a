package a.entity.gus.y.knowledgedb1.doc_z.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "doc_z";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_NAME = "name";
	public static final String COL_SIGN = "sign";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_DIFFICULTY_LEVEL = "difficulty_level";
	public static final String COL_ISSUE_LEVEL = "issue_level";
	public static final String COL_COMMENT = "comment";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_DATE_CREATED + ", " + COL_DATE_UPDATED + ", " + COL_NAME + ", "
				+ COL_SIGN + ", " + COL_DESCRIPTION + ", " + COL_STATE + ", "
				+ COL_DIFFICULTY_LEVEL + ", " + COL_ISSUE_LEVEL + ", " + COL_COMMENT
				+ ") VALUES (?,?,?,?,?,?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, new Date());
		st.setObject(2, null);
		st.setObject(3, data.get(COL_NAME));
		st.setObject(4, data.get(COL_SIGN));
		st.setObject(5, data.get(COL_DESCRIPTION));
		st.setObject(6, data.get(COL_STATE));
		st.setObject(7, data.get(COL_DIFFICULTY_LEVEL));
		st.setObject(8, data.get(COL_ISSUE_LEVEL));
		st.setObject(9, data.get(COL_COMMENT));
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
