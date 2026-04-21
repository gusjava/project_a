package a.entity.gus.y.entitydb1.jar_class.merge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260421";}

	public static final String TABLE_NAME = "jar_class";

	public static final String COL_JAR_SHA1 = "jar_sha1";
	public static final String COL_JAR_CLASS = "jar_class";
	public static final String COL_JAR_CLASS_PACKAGE = "jar_class_package";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String jarSha1 = (String) data.get(COL_JAR_SHA1);
		String jarClass = (String) data.get(COL_JAR_CLASS);
		String jarClassPackage = findPackage(jarClass);

		String sql = "MERGE INTO " + TABLE_NAME + " ("
		+ COL_JAR_SHA1 + ","
		+ COL_JAR_CLASS + ","
		+ COL_JAR_CLASS_PACKAGE + ") KEY ("
		+ COL_JAR_SHA1 + ","
		+ COL_JAR_CLASS + ") VALUES (?,?,?)";

		executeUpdate(cx, sql, jarSha1, jarClass, jarClassPackage);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
	
	private String findPackage(String path)
	{
		int lastDot = path.lastIndexOf('.');
		return lastDot != -1 ? path.substring(0, lastDot) : "";
	}
}
