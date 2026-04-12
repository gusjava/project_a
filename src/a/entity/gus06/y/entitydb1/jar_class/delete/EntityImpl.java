package a.entity.gus06.y.entitydb1.jar_class.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260101";}

	public static final String TABLE_NAME = "jar_class";

	public static final String COL_JAR_SHA1 = "jar_sha1";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String sha1 = (String) o[1];

		String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_JAR_SHA1 + "=?";
		executeUpdate(cx, sql, sha1);
	}

	private void executeUpdate(Connection cx, String sql, String param) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		st.executeUpdate();
		st.close();
	}
}