package a.entity.gus.y.knowledgedb1.todo.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260410";}

	public void p(Object obj) throws Exception
	{f(obj);}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long id = (Long) o[1];

		String sql = "DELETE FROM todo WHERE id=?";
		return executeUpdate(cx, sql, id)==1;
	}

	private int executeUpdate(Connection cx, String sql, Object param) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		int nb = st.executeUpdate();
		st.close();
		return nb;
	}
}
