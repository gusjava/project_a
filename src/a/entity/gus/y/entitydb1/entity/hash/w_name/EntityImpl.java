package a.entity.gus.y.entitydb1.entity.hash.w_name;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		String name = (String) o[1];

		String sql = "SELECT hash FROM entity WHERE entity_name = ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, name);
		
		ResultSet rs = st.executeQuery();
		String hash = null;
		if (rs.next()) hash = rs.getString(1);
		st.close();
		return hash;
	}
}
