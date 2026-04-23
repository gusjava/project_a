package a.entity.gus.y.entitydb1.entity_import.countbyimport;

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

	public String creationDate() {return "20260416";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "SELECT entity_import, COUNT(*) FROM entity_import " + 
		"GROUP BY entity_import";

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
