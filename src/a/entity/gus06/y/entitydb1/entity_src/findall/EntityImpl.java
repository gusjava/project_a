package a.entity.gus06.y.entitydb1.entity_src.findall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	public static final String TABLE_NAME = "entity_src";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT " + COL_ENTITY_NAME + ", " + COL_FILE_NAME +  " FROM " + TABLE_NAME + " ORDER BY " + COL_ENTITY_NAME;

		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Map data = new HashMap();
		while (rs.next())
		{
			String entityName = rs.getString(COL_ENTITY_NAME);
			String fileName = rs.getString(COL_FILE_NAME);
			
			if (!data.containsKey(entityName)) data.put(entityName, new ArrayList());
			((List) data.get(entityName)).add(fileName);
		}
		rs.close();
		st.close();
		return data;
	}
}
