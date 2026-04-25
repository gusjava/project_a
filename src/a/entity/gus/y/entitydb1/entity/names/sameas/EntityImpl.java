package a.entity.gus.y.entitydb1.entity.names.sameas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}


	private Service findHash;

	public EntityImpl() throws Exception {

		findHash = Outside.service(this,"gus.y.entitydb1.entity.hash.w_name");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		String name = (String) o[1];
		
		String hash = (String) findHash.t(new Object[]{cx,name});

		String sql = "SELECT entity_name FROM entity " + 
		"WHERE hash = ? AND entity_name != ? ORDER BY entity_name";
		
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, hash);
		st.setString(2, name);
		
		ResultSet rs = st.executeQuery();
		List data = new ArrayList();
		while (rs.next()) data.add(rs.getString(1));
		st.close();
		return data;
	}
}
