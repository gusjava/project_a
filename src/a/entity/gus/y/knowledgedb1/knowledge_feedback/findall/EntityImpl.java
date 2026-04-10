package a.entity.gus.y.knowledgedb1.knowledge_feedback.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge_feedback";
	public static final String COL_ID = "id";
	public static final String COL_ID_KNOWLEDGE = "id_knowledge";
	public static final String COL_DATE = "date";
	public static final String COL_VERDICT = "verdict";
	public static final String COL_CONTEXT = "context";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idKnowledge = (Long) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID_KNOWLEDGE + "=? ORDER BY " + COL_DATE;
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idKnowledge);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID);
			transfer(m, rs, COL_ID_KNOWLEDGE);
			transfer(m, rs, COL_DATE);
			transfer(m, rs, COL_VERDICT);
			transfer(m, rs, COL_CONTEXT);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
