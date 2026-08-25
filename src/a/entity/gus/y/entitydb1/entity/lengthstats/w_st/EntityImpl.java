package a.entity.gus.y.entitydb1.entity.lengthstats.w_st;

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
		String prefix = (String) o[1];

		String sql = "SELECT COUNT(*) AS c, MIN(length) AS mn, MAX(length) AS mx, AVG(length) AS av, STDDEV_POP(length) AS sd FROM entity WHERE entity_name LIKE ?";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, prefix + "%");
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		if (rs.next())
		{
			data.put("count", rs.getInt("c"));
			data.put("min", rs.getInt("mn"));
			data.put("max", rs.getInt("mx"));
			data.put("avg", rs.getDouble("av"));
			data.put("stddev", rs.getDouble("sd"));
		}
		st.close();
		return data;
	}
}