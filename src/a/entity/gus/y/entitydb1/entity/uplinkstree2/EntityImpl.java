package a.entity.gus.y.entitydb1.entity.uplinkstree2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public static final String TABLE_LINK = "entity_link";
	public static final String TABLE_ENTITY = "entity";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_LINK = "link";
	public static final String COL_FEATURES = "features";

	public EntityImpl() throws Exception {

	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		int maxDeep = ((Number) o[2]).intValue();

		Set visited = new HashSet();
		visited.add(entityName);
		return buildTree(cx, entityName, maxDeep, visited, 0);
	}

	private String getDescription(Connection cx, String entityName) throws Exception {
		String sql = "SELECT " + COL_FEATURES + " FROM " + TABLE_ENTITY + " WHERE " + COL_ENTITY_NAME + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();
		String features = null;
		if (rs.next()) features = rs.getString(COL_FEATURES);
		st.close();
		if (features == null || features.trim().isEmpty()) return entityName;
		return entityName + "-" + features.toUpperCase();
	}

	private List buildTree(Connection cx, String entityName, int maxDeep, Set visited, int depth) throws Exception {
		List result = new ArrayList();
		if (depth >= maxDeep) return result;

		String sql = "SELECT " + COL_ENTITY_NAME + " FROM " + TABLE_LINK + " WHERE " + COL_LINK + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();

		List uplinks = new ArrayList();
		while (rs.next()) {
			uplinks.add(rs.getObject(COL_ENTITY_NAME));
		}
		st.close();

		for (int i = 0; i < uplinks.size(); i++) {
			String uplink = (String) uplinks.get(i);
			if (visited.contains(uplink)) continue;
			visited.add(uplink);
			String desc = getDescription(cx, uplink);
			List children = buildTree(cx, uplink, maxDeep, visited, depth + 1);
			result.add(new Object[]{desc, children});
		}
		return result;
	}
}
