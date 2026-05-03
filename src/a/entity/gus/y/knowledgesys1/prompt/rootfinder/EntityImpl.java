package a.entity.gus.y.knowledgesys1.prompt.rootfinder;

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
		List keywords = (List) obj;
		if (keywords == null || keywords.isEmpty()) return null;

		Connection cx = cx();

		if (keywords.size() >= 2) {
			Map row = exactMatch(cx, (String) keywords.get(0), (String) keywords.get(1));
			if (row != null) return row;
		}

		return tagScore(cx, keywords);
	}

	private Map exactMatch(Connection cx, String action, String object) throws Exception
	{
		String sql = "SELECT id, code, action, object, description, state, preprocessor FROM knowledge WHERE UPPER(action)=UPPER(?) AND UPPER(object)=UPPER(?) LIMIT 1";
		PreparedStatement ps = cx.prepareStatement(sql);
		ps.setString(1, action);
		ps.setString(2, object);
		ResultSet rs = ps.executeQuery();
		Map row = rs.next() ? toMap(rs) : null;
		rs.close();
		ps.close();
		return row;
	}

	private Map tagScore(Connection cx, List keywords) throws Exception
	{
		StringBuilder ph = new StringBuilder();
		for (int i = 0; i < keywords.size(); i++) {
			if (i > 0) ph.append(",");
			ph.append("?");
		}
		String sql = "SELECT k.id, k.code, k.action, k.object, k.description, k.state, k.preprocessor, kt.tag FROM knowledge k JOIN knowledge_tag kt ON kt.id_knowledge=k.id WHERE UPPER(kt.tag) IN (" + ph + ")";
		PreparedStatement ps = cx.prepareStatement(sql);
		for (int i = 0; i < keywords.size(); i++)
			ps.setString(i + 1, ((String) keywords.get(i)).toUpperCase());
		ResultSet rs = ps.executeQuery();

		Map scores = new LinkedHashMap();
		Map rows = new LinkedHashMap();
		int n = keywords.size();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String tag = rs.getString("tag").toUpperCase();
			for (int i = 0; i < keywords.size(); i++) {
				if (tag.equals(((String) keywords.get(i)).toUpperCase())) {
					double w = (double)(n - i);
					Double prev = (Double) scores.get(id);
					scores.put(id, prev == null ? w : prev + w);
				}
			}
			if (!rows.containsKey(id)) rows.put(id, toMap(rs));
		}
		rs.close();
		ps.close();

		Long bestId = null;
		double bestScore = -1;
		for (Object key : scores.keySet()) {
			double s = (Double) scores.get(key);
			if (s > bestScore) { bestScore = s; bestId = (Long) key; }
		}
		return bestId != null ? (Map) rows.get(bestId) : null;
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