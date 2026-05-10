package a.entity.gus.y.knowledgedb1.knowledge.info.w_code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	private Service rsToMap;

	public EntityImpl() throws Exception
	{
		rsToMap = Outside.service(this, "gus.y.knowledgedb1.util.knowledge.rstomap1");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] args = (Object[]) obj;
		Connection cx = (Connection) args[0];
		String code = (String) args[1];

		String sql = "SELECT * FROM knowledge WHERE code=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, code);
		ResultSet rs = st.executeQuery();

		if (!rs.next()) { st.close(); return null; }
		Object result = rsToMap.t(rs);
		st.close();
		return result;
	}
}