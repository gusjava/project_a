package a.entity.gus.y.knowledgedb1.todo.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "todo";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_CODE = "code";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIPTION = "description";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object code = data.get(COL_CODE);
		Object title = data.get(COL_TITLE);
		Object description = data.get(COL_DESCRIPTION);

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_DATE_CREATED + ", " + COL_CODE + ", " + COL_TITLE + ", " + COL_DESCRIPTION + ") VALUES (?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, new Date());
		st.setObject(2, code);
		st.setObject(3, title);
		st.setObject(4, description);
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
