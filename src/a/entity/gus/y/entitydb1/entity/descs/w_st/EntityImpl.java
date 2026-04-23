package a.entity.gus.y.entitydb1.entity.descs.w_st;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		String prefix = (String) o[1];

		String sql = "SELECT entity_name, features FROM entity " + 
		"WHERE entity_name LIKE ? ORDER BY entity_name";

		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, prefix + "%");
		ResultSet rs = st.executeQuery();

		List data = new ArrayList();
		while (rs.next())
		{
			String entityName = rs.getString("entity_name");
			String features = rs.getString("features");
			data.add(entityName + "-" + features);
		}
		st.close();
		return data;
	}
}
