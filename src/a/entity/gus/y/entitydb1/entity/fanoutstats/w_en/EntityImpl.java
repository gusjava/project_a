package a.entity.gus.y.entitydb1.entity.fanoutstats.w_en;

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
		String suffix = (String) o[1];
		String pattern = "%" + suffix;

		Map data = new HashMap();

		String statsSql = "SELECT COUNT(*) AS c, MIN(call_nb) AS mn, MAX(call_nb) AS mx, AVG(call_nb) AS av, STDDEV_POP(call_nb) AS sd FROM entity WHERE entity_name LIKE ?";
		PreparedStatement st1 = cx.prepareStatement(statsSql);
		st1.setString(1, pattern);
		ResultSet rs1 = st1.executeQuery();
		if (rs1.next())
		{
			data.put("count", rs1.getInt("c"));
			data.put("min", rs1.getInt("mn"));
			data.put("max", rs1.getInt("mx"));
			data.put("avg", rs1.getDouble("av"));
			data.put("stddev", rs1.getDouble("sd"));
		}
		st1.close();

		String freqSql = "SELECT call_nb, COUNT(*) AS c FROM entity WHERE entity_name LIKE ? GROUP BY call_nb";
		PreparedStatement st2 = cx.prepareStatement(freqSql);
		st2.setString(1, pattern);
		ResultSet rs2 = st2.executeQuery();
		Map freq = new HashMap();
		while (rs2.next())
		{
			int callNb = rs2.getInt("call_nb");
			int count = rs2.getInt("c");
			freq.put(String.valueOf(callNb), count);
		}
		st2.close();
		data.put("freq", freq);

		return data;
	}
}