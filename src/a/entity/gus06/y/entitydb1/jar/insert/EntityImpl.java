package a.entity.gus06.y.entitydb1.jar.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251228";}

	public static final String TABLE_NAME = "jar";
	
	public static final String COL_SHA1 = "sha1";
	public static final String COL_MD5 = "md5";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_FILE_MODIF_DATE = "file_modif_date";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_MAVEN_ID = "maven_id";
	public static final String COL_MAVEN_RETRIEVE_METHOD = "maven_retrieve_method";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Object sha1 = data.get(COL_SHA1);
		Object md5 = data.get(COL_MD5);
		Object fileName = data.get(COL_FILE_NAME);
		Object fileModifDate = data.get(COL_FILE_MODIF_DATE);
		Object creationDate = data.get(COL_CREATION_DATE);
		Object mavenId = data.get(COL_MAVEN_ID);
		Object mavenRetrieveMethod = data.get(COL_MAVEN_RETRIEVE_METHOD);

		String sql = "INSERT INTO " + TABLE_NAME + " (" 
		+ COL_SHA1 + "," 
		+ COL_MD5 + "," 
		+ COL_FILE_NAME + "," 
		+ COL_FILE_MODIF_DATE + ","
		+ COL_CREATION_DATE + ","
		+ COL_MAVEN_ID + ","
		+ COL_MAVEN_RETRIEVE_METHOD + ") VALUES (?,?,?,?,?,?,?)";

		executeUpdate(cx, sql, sha1, md5, fileName, fileModifDate, creationDate, mavenId, mavenRetrieveMethod);
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
