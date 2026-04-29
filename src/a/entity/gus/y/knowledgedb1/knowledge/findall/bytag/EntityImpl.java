package a.entity.gus.y.knowledgedb1.knowledge.findall.bytag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260429";}

	private Service rsToMap;

	public EntityImpl() throws Exception
	{
		rsToMap = Outside.service(this, "gus.y.knowledgedb1.util.knowledge.rstomap");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] args = (Object[]) obj;
		Connection cx = (Connection) args[0];
		String tag = (String) args[1];

		String sql = "SELECT k.* FROM knowledge k JOIN knowledge_tag kt ON k.id=kt.id_knowledge WHERE kt.tag=? ORDER BY k.id";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, tag);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next())
		{
			Map m = (Map) rsToMap.t(rs);
			data.add(m);
		}
		st.close();
		return data;
	}
}
