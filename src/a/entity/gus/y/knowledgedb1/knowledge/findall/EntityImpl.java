package a.entity.gus.y.knowledgedb1.knowledge.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	private Service rsToMap;

	public EntityImpl() throws Exception
	{
		rsToMap = Outside.service(this,"gus.y.knowledgedb1.util.knowledge.rstomap1");
	}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT * FROM knowledge ORDER BY id";
		
		PreparedStatement st = cx.prepareStatement(sql);
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
