package a.entity.gus06.y.entitydb1.jar_import.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251228";}

	public static final String TABLE_NAME = "jar_import";

	public static final String COL_JAR_SHA1 = "jar_sha1";
	public static final String COL_JAR_IMPORT = "jar_import";
	public static final String COL_JAR_IMPORT_PACKAGE = "jar_import_package";
	public static final String COL_JAR_IMPORT_WILDCARD = "jar_import_wildcard";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String jarSha1 = (String) data.get(COL_JAR_SHA1);
		String jarImport = (String) data.get(COL_JAR_IMPORT);
		
		Boolean wildcard = jarImport.endsWith(".*");
		String jarImportPackage = findPackage(jarImport);
		
		String sql = "INSERT INTO " + TABLE_NAME + " (" 
		+ COL_JAR_SHA1 + "," 
		+ COL_JAR_IMPORT + "," 
		+ COL_JAR_IMPORT_PACKAGE + "," 
		+ COL_JAR_IMPORT_WILDCARD + ") VALUES (?,?,?,?)";

		executeUpdate(cx, sql, jarSha1, jarImport, jarImportPackage, wildcard);
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
