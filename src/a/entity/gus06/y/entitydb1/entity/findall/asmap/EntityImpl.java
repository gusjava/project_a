package a.entity.gus06.y.entitydb1.entity.findall.asmap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}


	public static final String TABLE_NAME = "entity";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALL_NB = "call_nb";
	public static final String COL_FILE_NB = "file_nb";

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
			transfer(m, rs, COL_ENTITY_NAME);
			transfer(m, rs, COL_FEATURES);
			transfer(m, rs, COL_CREATION_DATE);
			transfer(m, rs, COL_LENGTH);
			transfer(m, rs, COL_CALL_NB);
			transfer(m, rs, COL_FILE_NB);

			String entityName = (String) m.get(COL_ENTITY_NAME);
			data.put(entityName, m);
		}
		rs.close();
		return data;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}