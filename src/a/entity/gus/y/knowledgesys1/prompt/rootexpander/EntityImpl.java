package a.entity.gus.y.knowledgesys1.prompt.rootexpander;

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
		Map root = (Map) obj;

		Connection cx = cx();
		List result = new ArrayList();
		Map seen = new LinkedHashMap();
		int idx = 0;

		Long rootId = (Long) root.get("id");
		seen.put(rootId, Boolean.TRUE);
		result.add(root);

		while (idx < result.size()) {
			Map current = (Map) result.get(idx++);
			Long id = (Long) current.get("id");
			List required = requiredLinks(cx, id);
			for (int i = 0; i < required.size(); i++) {
				Map linked = (Map) required.get(i);
				Long linkedId = (Long) linked.get("id");
				if (!seen.containsKey(linkedId)) {
					seen.put(linkedId, Boolean.TRUE);
					result.add(linked);
				}
			}
		}
		return result;
	}

	private List requiredLinks(Connection cx, Long id) throws Exception
	{
		String sql = "SELECT k.id, k.code, k.action, k.object, k.description, k.state, k.preprocessor FROM knowledge k JOIN knowledge_link kl ON kl.id_linked=k.id WHERE kl.id_linker=? AND kl.type='REQUIRED'";
		PreparedStatement ps = cx.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		List list = new ArrayList();
		while (rs.next()) list.add(toMap(rs));
		rs.close();
		ps.close();
		return list;
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