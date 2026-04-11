package a.entity.gus.y.roadmapdb1.sql.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	private Service buildJson;

	public EntityImpl() throws Exception {
		buildJson = Outside.service(this, "gus.x.json.build1");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String sql = (String) o[1];

		PreparedStatement st = cx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int result = st.executeUpdate();
		if (result == 0) return null;

		if (result > 1) throw new Exception("Many row were affected when executing insert query: " + result + " (sql=" + sql + ")");

		ResultSet rs = st.getGeneratedKeys();
		if (!rs.next()) return null;

		Object id = rs.getObject(1);
		st.close();
		return buildJson.t(id);
	}
}
