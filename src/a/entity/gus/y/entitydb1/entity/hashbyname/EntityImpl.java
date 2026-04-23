package a.entity.gus.y.entitydb1.entity.hashbyname;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260423";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "SELECT entity_name, hash FROM entity";
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		Map data = new HashMap();
		while (rs.next())
		{
			String entityName = rs.getString("entity_name");
			String hash = rs.getString("hash");
			data.put(entityName, hash);
		}
		st.close();
		return data;
	}
}