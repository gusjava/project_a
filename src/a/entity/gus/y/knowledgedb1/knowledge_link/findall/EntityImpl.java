package a.entity.gus.y.knowledgedb1.knowledge_link.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260429";}

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		String sql =
			"SELECT kl.id_linker, kl.id_linked, kl.type, " +
			"k1.code || ':' || k1.action || ':' || k1.object AS linker, " +
			"k2.code || ':' || k2.action || ':' || k2.object AS linked " +
			"FROM knowledge_link kl " +
			"JOIN knowledge k1 ON k1.id = kl.id_linker " +
			"JOIN knowledge k2 ON k2.id = kl.id_linked " +
			"ORDER BY k1.code, k2.code";

		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, "id_linker");
			transfer(m, rs, "id_linked");
			transfer(m, rs, "type");
			transfer(m, rs, "linker");
			transfer(m, rs, "linked");
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}