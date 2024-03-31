package a.entity.gus.y.entitydb1.entity.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240112";}

	public static final String TABLE_NAME = "entity";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALL_NB = "call_nb";
	public static final String COL_FILE_NB = "file_nb";

	public EntityImpl() throws Exception {

	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object entityName = data.get(COL_ENTITY_NAME);
		Object features = data.get(COL_FEATURES);
		Object creationDate = data.get(COL_CREATION_DATE);
		Object length = data.get(COL_LENGTH);
		Object callNb = data.get(COL_CALL_NB);
		Object fileNb = data.get(COL_FILE_NB);

		String sql = "UPDATE " + TABLE_NAME + " SET " 
		+ COL_FEATURES + "=?, " 
		+ COL_CREATION_DATE + "=?, " 
		+ COL_LENGTH + "=?, " 
		+ COL_CALL_NB + "=?, " 
		+ COL_FILE_NB + "=? " 
		+ "WHERE " + COL_ENTITY_NAME + "=?";

		executeUpdate(cx, sql, features, creationDate, length, callNb, fileNb, entityName);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
