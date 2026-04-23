package a.entity.gus.y.entitydb1.entity.featuresbyname;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "SELECT entity_name, features FROM entity";
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		Map data = new HashMap();
		while (rs.next()) {
			String entityName = rs.getString("entity_name");
			String features = rs.getString("features");
			data.put(entityName, features);
		}
		st.close();
		return data;
	}
}