package a.entity.gus.y.entitydb1.entity_compile_err.deleteall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240112";}

	public static final String TABLE_NAME = "entity_compile_err";

	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;

		String sql = "DELETE FROM " + TABLE_NAME;
		executeUpdate(cx, sql);
	}

	private void executeUpdate(Connection cx, String sql) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		st.executeUpdate();
		st.close();
	}
}
