package a.entity.gus.y.entitydb1.entity.fanoutexternalstats.w_co;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
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
		String pattern = "%" + fragment + "%";

		String sql = "SELECT e.entity_name AS name, (SELECT COUNT(*) FROM entity_link l WHERE l.entity_name = e.entity_name AND l.link NOT LIKE ?) AS fanout FROM entity e WHERE e.entity_name LIKE ?";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, pattern);
		st.setString(2, pattern);
		ResultSet rs = st.executeQuery();

		int count = 0, sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		String topName = null;
		Map freq = new HashMap();
		Map values = new HashMap();

		while (rs.next())
		{
			String name = rs.getString("name");
			int fanout = rs.getInt("fanout");

			count++;
			sum += fanout;
			if (fanout < min) min = fanout;
			if (fanout > max)
			{
				max = fanout;
				topName = name;
			}

			String key = String.valueOf(fanout);
			Integer c = (Integer) freq.get(key);
			freq.put(key, c == null ? 1 : c + 1);
			values.put(name, fanout);
		}
		st.close();

		Map data = new HashMap();
		if (count == 0)
		{
			data.put("count", 0);
			return data;
		}

		double avg = ((double) sum) / count;
		double sqSum = 0;
		Iterator it = values.values().iterator();
		while (it.hasNext())
		{
			int v = (Integer) it.next();
			double diff = v - avg;
			sqSum += diff * diff;
		}
		double stddev = Math.sqrt(sqSum / count);

		Map top = new HashMap();
		top.put("entity", topName);
		top.put("value", max);

		data.put("count", count);
		data.put("min", min);
		data.put("max", max);
		data.put("avg", avg);
		data.put("stddev", stddev);
		data.put("freq", freq);
		data.put("top", top);

		return data;
	}
}
