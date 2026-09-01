package a.entity.gus.y.knowledgedb1.rule.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "rule";
	public static final String COL_CODE = "code";
	public static final String COL_CONTENT = "content";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_CODE + ", " + COL_CONTENT
				+ ") VALUES (?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, data.get(COL_CODE));
		st.setObject(2, data.get(COL_CONTENT));
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}