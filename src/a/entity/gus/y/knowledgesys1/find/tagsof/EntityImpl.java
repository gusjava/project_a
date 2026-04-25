package a.entity.gus.y.knowledgesys1.find.tagsof;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	public EntityImpl() throws Exception {}

	public Object t(Object obj) throws Exception {
		Object[] o  = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String table  = (String) o[1];
		String id     = (String) o[2];
		String fk     = "ID_" + table.toUpperCase();
		String sql    = "SELECT TAG FROM " + table + "_tag WHERE " + fk + " = " + id;
		Statement st  = cx.createStatement();
		ResultSet rs  = st.executeQuery(sql);
		List result   = new ArrayList();
		while(rs.next()) result.add(rs.getString(1));
		st.close();
		return result;
	}
}