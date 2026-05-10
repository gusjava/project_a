package a.entity.gus.y.knowledgedb1.knowledge.displays.w_tags;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	public Object t(Object obj) throws Exception
	{
		Object[] args = (Object[]) obj;
		Connection cx = (Connection) args[0];
		List tags = (List) args[1];

		if (tags.isEmpty()) return new ArrayList();

		StringBuilder placeholders = new StringBuilder();
		for (int i = 0; i < tags.size(); i++)
		{
			if (i > 0) placeholders.append(",");
			placeholders.append("?");
		}

		String sql =
			"SELECT k.code, k.action, k.object FROM knowledge k " +
			"JOIN knowledge_tag kt ON k.id=kt.id_knowledge " +
			"WHERE kt.tag IN (" + placeholders + ") " +
			"GROUP BY k.id, k.code, k.action, k.object " +
			"HAVING COUNT(DISTINCT kt.tag)=? " +
			"ORDER BY k.code";

		PreparedStatement st = cx.prepareStatement(sql);
		int idx = 1;
		for (Object tag : tags) st.setString(idx++, (String) tag);
		st.setInt(idx, tags.size());
		ResultSet rs = st.executeQuery();

		List result = new ArrayList();
		while (rs.next())
		{
			String code = rs.getString("code");
			String action = rs.getString("action");
			String object = rs.getString("object");
			
			result.add(code + ":" + code + ":" + object);
		}
		st.close();
		return result;
	}
}