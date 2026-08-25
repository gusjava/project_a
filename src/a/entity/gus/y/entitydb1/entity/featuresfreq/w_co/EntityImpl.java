package a.entity.gus.y.entitydb1.entity.featuresfreq.w_co;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260825";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String fragment = (String) o[1];

		String sql = "SELECT features, COUNT(*) AS c FROM entity " +
			"WHERE entity_name LIKE ? GROUP BY features";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, "%" + fragment + "%");
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next())
		{
			String features = rs.getString("features");
			int count = rs.getInt("c");
			data.put(features, count);
		}
		st.close();
		return data;
	}
}