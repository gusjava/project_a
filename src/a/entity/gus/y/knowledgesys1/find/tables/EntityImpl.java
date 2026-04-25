package a.entity.gus.y.knowledgesys1.find.tables;

import java.sql.*;
import java.util.*;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	public EntityImpl() throws Exception {}

	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		Statement st  = cx.createStatement();
		ResultSet rs  = st.executeQuery("SHOW TABLES");
		List result   = new ArrayList();
		while(rs.next()) result.add(rs.getString(1));
		st.close();
		return result;
	}
}