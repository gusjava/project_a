package a.entity.gus.y.entitydb1.entity.internalhubs.w_en;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260825";}

	private static final int CAP = 10;

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String suffix = (String) o[1];
		String pattern = "%" + suffix;

		String sql = "SELECT e.entity_name AS name, "
			+ "(SELECT COUNT(*) FROM entity_link l WHERE l.link = e.entity_name AND l.entity_name LIKE ?) AS fanin, "
			+ "(SELECT COUNT(*) FROM entity_link l WHERE l.entity_name = e.entity_name AND l.link LIKE ?) AS fanout "
			+ "FROM entity e WHERE e.entity_name LIKE ?";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, pattern);
		st.setString(2, pattern);
		st.setString(3, pattern);
		ResultSet rs = st.executeQuery();

		List hub = new ArrayList();
		List source = new ArrayList();
		List leaf = new ArrayList();
		int isolatedCount = 0;
		List isolatedSample = new ArrayList();

		while (rs.next())
		{
			String name = rs.getString("name");
			int fanin = rs.getInt("fanin");
			int fanout = rs.getInt("fanout");

			if (fanin > 0 && fanout > 0)
			{
				Map e = new HashMap();
				e.put("entity", name);
				e.put("fanin", fanin);
				e.put("fanout", fanout);
				e.put("sum", fanin + fanout);
				insertRanked(hub, e, "sum", CAP);
			}
			else if (fanout > 0)
			{
				Map e = new HashMap();
				e.put("entity", name);
				e.put("fanout", fanout);
				insertRanked(source, e, "fanout", CAP);
			}
			else if (fanin > 0)
			{
				Map e = new HashMap();
				e.put("entity", name);
				e.put("fanin", fanin);
				insertRanked(leaf, e, "fanin", CAP);
			}
			else
			{
				isolatedCount++;
				if (isolatedSample.size() < CAP) isolatedSample.add(name);
			}
		}
		st.close();

		Map isolated = new HashMap();
		isolated.put("count", isolatedCount);
		isolated.put("sample", isolatedSample);

		Map data = new HashMap();
		data.put("hub", hub);
		data.put("source", source);
		data.put("leaf", leaf);
		data.put("isolated", isolated);

		return data;
	}

	private void insertRanked(List list, Map entry, String rankKey, int cap)
	{
		int val = ((Integer) entry.get(rankKey)).intValue();
		int idx = 0;
		while (idx < list.size() && ((Integer) ((Map) list.get(idx)).get(rankKey)).intValue() >= val) idx++;
		list.add(idx, entry);
		if (list.size() > cap) list.remove(list.size() - 1);
	}
}
