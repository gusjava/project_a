package a.entity.gus.y.entitydb1.entity_import.names.w_import;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		String importName = (String) o[1];

		String sql = "SELECT entity_name FROM entity_import " + 
		"WHERE entity_import = ? ORDER BY entity_name";
		
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, importName);
		
		ResultSet rs = st.executeQuery();
		List data = new ArrayList();
		while (rs.next()) data.add(rs.getString(1));
		st.close();
		return data;
	}
}