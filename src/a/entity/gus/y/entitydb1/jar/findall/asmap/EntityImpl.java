package a.entity.gus.y.entitydb1.jar.findall.asmap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260416";}


	public static final String TABLE_NAME = "jar";

	public static final String COL_SHA1 = "sha1";
	public static final String COL_MD5 = "md5";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_FILE_MODIF_DATE = "file_modif_date";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_MAVEN_ID = "maven_id";
	public static final String COL_MAVEN_RETRIEVE_METHOD = "maven_retrieve_method";

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "SELECT * FROM " + TABLE_NAME;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		Map data = new HashMap();
		while (rs.next())
		{
			Map m = new HashMap();
			transfer(m, rs, COL_SHA1);
			transfer(m, rs, COL_MD5);
			transfer(m, rs, COL_FILE_NAME);
			transfer(m, rs, COL_FILE_MODIF_DATE);
			transfer(m, rs, COL_CREATION_DATE);
			transfer(m, rs, COL_MAVEN_ID);
			transfer(m, rs, COL_MAVEN_RETRIEVE_METHOD);

			String fileName = (String) m.get(COL_FILE_NAME);
			data.put(fileName, m);
		}
		rs.close();
		st.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}