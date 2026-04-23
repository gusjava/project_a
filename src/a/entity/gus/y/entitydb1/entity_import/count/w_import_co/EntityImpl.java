package a.entity.gus.y.entitydb1.entity_import.count.w_import_co;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260422";}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String fragment = (String) o[1];

		String sql = "SELECT COUNT(*) FROM entity_import WHERE entity_import LIKE ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, "%" + fragment + "%");
		
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		st.close();
		return count;
	}
}