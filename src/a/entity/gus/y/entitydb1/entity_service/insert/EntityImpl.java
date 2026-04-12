package a.entity.gus.y.entitydb1.entity_service.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240112";}

	public static final String TABLE_NAME = "entity_service";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_CALL = "call";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		String call = (String) o[2];

		try {
			String sql = "INSERT INTO " + TABLE_NAME + " (" 
			+ COL_ENTITY_NAME + "," + COL_CALL + ") VALUES (?,?) ";
			executeUpdate(cx, sql, entityName, call);
		} catch (SQLException e) {
			String message = "Failed to insert row with entityName=" + entityName + " and call=" + call;
			throw new Exception(message, e);
		}
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
