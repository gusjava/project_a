package a.entity.gus.y.entitydb1.entity_xyz_err.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240119";}
	
	public static final String TABLE_NAME = "entity_xyz_err";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_LINK = "link";
	public static final String COL_POS = "pos";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		String link = (String) o[2];
		Integer pos = (Integer) o[3];

		String sql = "INSERT INTO " + TABLE_NAME 
		+ " (" + COL_ENTITY_NAME + "," + COL_LINK + "," + COL_POS
				+ ") VALUES (?,?,?)";

		try {
			executeUpdate(cx, sql, entityName, link, pos);
		}
		catch(SQLException e) {
			String message = "failed to insert xyzErr with entityName="+entityName+", link="+link+", pos="+pos;
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
