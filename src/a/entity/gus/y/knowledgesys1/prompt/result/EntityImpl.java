package a.entity.gus.y.knowledgesys1.prompt.result;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260503";}

	private Service engine;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		List cluster = (List) json.get("cluster");
		List extraCodes = (List) json.get("extra_codes");

		Map seen = new LinkedHashMap();
		List result = new ArrayList();

		if (cluster != null) {
			for (int i = 0; i < cluster.size(); i++) {
				Map row = (Map) cluster.get(i);
				Long id = (Long) row.get("id");
				if (!seen.containsKey(id)) {
					seen.put(id, Boolean.TRUE);
					result.add(row);
				}
			}
		}

		if (extraCodes != null && !extraCodes.isEmpty()) {
			Connection cx = cx();
			for (int i = 0; i < extraCodes.size(); i++) {
				String code = (String) extraCodes.get(i);
				Map row = findByCode(cx, code);
				if (row != null) {
					Long id = (Long) row.get("id");
					if (!seen.containsKey(id)) {
						seen.put(id, Boolean.TRUE);
						result.add(row);
					}
				}
			}
		}
		return result;
	}

	private Map findByCode(Connection cx, String code) throws Exception
	{
		String sql = "SELECT id, code, action, object, description, state, preprocessor FROM knowledge WHERE code=? LIMIT 1";
		PreparedStatement ps = cx.prepareStatement(sql);
		ps.setString(1, code);
		ResultSet rs = ps.executeQuery();
		Map row = rs.next() ? toMap(rs) : null;
		rs.close();
		ps.close();
		return row;
	}

	private Map toMap(ResultSet rs) throws Exception
	{
		Map row = new LinkedHashMap();
		row.put("id", rs.getLong("id"));
		row.put("code", rs.getString("code"));
		row.put("action", rs.getString("action"));
		row.put("object", rs.getString("object"));
		row.put("description", rs.getString("description"));
		row.put("state", rs.getString("state"));
		row.put("preprocessor", rs.getString("preprocessor"));
		return row;
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}