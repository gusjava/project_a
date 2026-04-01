package a.entity.gus06.y.entitydb1.entity_import.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251215";}

	public static final String TABLE_NAME = "entity_import";

	public static final String COL_ENTITY_NAME = "entity_name";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ENTITY_NAME + "=?";
		executeUpdate(cx, sql, entityName);
	}

	private void executeUpdate(Connection cx, String sql, String param) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		st.executeUpdate();
		st.close();
	}
}
