package a.entity.gus.y.entitydb1.entity_src_save.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240717";}

	public static final String TABLE_NAME = "entity_src_save";

	public static final String COL_DATE = "date";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_SRC = "src";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		String fileName = (String) o[2];
		String src = (String) o[3];
		
		Date date = new Date();

		try {
			String sql = "INSERT INTO " + TABLE_NAME + " (" 
			+ COL_DATE + "," 
			+ COL_ENTITY_NAME + "," 
			+ COL_FILE_NAME + "," 
			+ COL_SRC 
			+ ") VALUES (?,?,?,?) ";
			
			executeUpdate(cx, sql, date, entityName, fileName, src);
		} catch (SQLException e) {
			String message = "Failed to insert row with entityName=" + entityName + " and fileName=" + fileName;
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
