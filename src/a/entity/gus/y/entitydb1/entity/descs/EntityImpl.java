package a.entity.gus.y.entitydb1.entity.descs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;

		String sql = "SELECT entity_name, features FROM entity ORDER BY entity_name";
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);

		List data = new ArrayList();
		while (rs.next())
		{
			String entityName = rs.getString("entity_name");
			String features = rs.getString("features");
			data.add(entityName + "-" + features);
		}
		return data;
	}
}
