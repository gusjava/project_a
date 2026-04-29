package a.entity.gus.y.knowledgedb1.todo_tag.countbytag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260429";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "SELECT tag, COUNT(*) FROM todo_tag GROUP BY tag";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next())
		{
			String tag = rs.getString(1);
			Long count = rs.getLong(2);
			data.put(tag, count);
		}
		st.close();
		return data;
	}
}