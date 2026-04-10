package a.entity.gus.y.knowledgedb1.knowledge_feedback.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge_feedback";
	public static final String COL_ID_KNOWLEDGE = "id_knowledge";
	public static final String COL_DATE = "date";
	public static final String COL_VERDICT = "verdict";
	public static final String COL_CONTEXT = "context";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object idKnowledge = data.get(COL_ID_KNOWLEDGE);
		Object verdict = data.get(COL_VERDICT);
		Object context = data.get(COL_CONTEXT);

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_ID_KNOWLEDGE + ", " + COL_DATE + ", " + COL_VERDICT + ", " + COL_CONTEXT
				+ ") VALUES (?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, idKnowledge);
		st.setObject(2, new Date());
		st.setObject(3, verdict);
		st.setObject(4, context);
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
