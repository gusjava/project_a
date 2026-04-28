package a.entity.gus.y.knowledgedb1.knowledge_link.find1;

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
	public String creationDate() {return "20260410";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idLinker = (Long) o[1];

		String sql = "SELECT id_linked, type FROM knowledge_link WHERE id_linker=?";
				
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idLinker);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next())
		{
			Map m = new HashMap();
			transfer(m, rs, "id_linked");
			transfer(m, rs, "type");
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
