package a.entity.gus.y.knowledgedb1.knowledge_tag.find2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260427";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String tag = (String) o[1];

		String sql = "SELECT id_knowledge FROM knowledge_tag WHERE tag=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, tag);
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next()) data.add(rs.getObject("id_knowledge"));
		st.close();
		return data;
	}
}
