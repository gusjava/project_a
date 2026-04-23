package a.entity.gus.y.entitydb1.entity.countbyhash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260423";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "SELECT hash, COUNT(*) FROM entity GROUP BY hash";

		try (
			PreparedStatement st = cx.prepareStatement(sql);
			ResultSet rs = st.executeQuery())
		{
			Map data = new HashMap();
			while (rs.next())
			{
				String hash = rs.getString(1);
				data.put(hash, rs.getInt(2));
			}
			return data;
		}
	}
}
