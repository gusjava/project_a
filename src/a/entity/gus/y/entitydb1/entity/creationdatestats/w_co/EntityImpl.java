package a.entity.gus.y.entitydb1.entity.creationdatestats.w_co;

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

		String sql = "SELECT COUNT(*) AS c, MIN(creation_date) AS mn, MAX(creation_date) AS mx, DATEDIFF('DAY', MIN(creation_date), MAX(creation_date)) AS spread FROM entity WHERE entity_name LIKE ?";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, "%" + fragment + "%");
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		if (rs.next())
		{
			data.put("count", rs.getInt("c"));
			data.put("min", rs.getString("mn"));
			data.put("max", rs.getString("mx"));
			data.put("spreadDays", rs.getInt("spread"));
		}
		st.close();
		return data;
	}
}