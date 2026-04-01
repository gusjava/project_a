package a.entity.gus06.y.entitydb1.entity_import.count.byimport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251215";}

	public static final String TABLE_NAME = "entity_import";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_ENTITY_IMPORT = "entity_import";

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT " + COL_ENTITY_IMPORT + ", COUNT(*) FROM " + 
		TABLE_NAME + " GROUP BY " + COL_ENTITY_IMPORT;

		try (
			PreparedStatement st = cx.prepareStatement(sql);
			ResultSet rs = st.executeQuery())
		{
			Map data = new HashMap();
			while (rs.next())
			{
				String import_ = rs.getString(1);
				Integer count = (Integer) rs.getInt(2);
				data.put(import_, count);
			}
			return data;
		}
	}
}
