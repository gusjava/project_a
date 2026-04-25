package a.entity.gus.y.knowledgedb1.knowledge.count;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "SELECT COUNT(*) FROM knowledge";
		PreparedStatement st = cx.prepareStatement(sql);

		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		st.close();
		return count;
	}
}
