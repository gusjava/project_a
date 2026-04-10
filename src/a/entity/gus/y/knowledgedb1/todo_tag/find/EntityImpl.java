package a.entity.gus.y.knowledgedb1.todo_tag.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "todo_tag";
	public static final String COL_ID_TODO = "id_todo";
	public static final String COL_TAG = "tag";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long idTodo = (Long) o[1];

		String sql = "SELECT " + COL_TAG + " FROM " + TABLE_NAME + " WHERE " + COL_ID_TODO + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, idTodo);
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next())
			data.add(rs.getString(COL_TAG));
		st.close();
		return data;
	}
}
