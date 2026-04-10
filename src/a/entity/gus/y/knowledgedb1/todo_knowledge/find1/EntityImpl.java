package a.entity.gus.y.knowledgedb1.todo_knowledge.find1;

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

	public static final String TABLE_NAME = "todo_knowledge";
	public static final String COL_ID_TODO = "id_todo";
	public static final String COL_ID_KNOWLEDGE = "id_knowledge";
	public static final String COL_TYPE = "type";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idTodo = (Long) o[1];

		String sql = "SELECT " + COL_ID_KNOWLEDGE + ", " + COL_TYPE + " FROM " + TABLE_NAME
				+ " WHERE " + COL_ID_TODO + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idTodo);
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next()) {
			Map m = new HashMap();
			transfer(m, rs, COL_ID_KNOWLEDGE);
			transfer(m, rs, COL_TYPE);
			data.add(m);
		}
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
