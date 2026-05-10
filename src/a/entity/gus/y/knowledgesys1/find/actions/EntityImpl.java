package a.entity.gus.y.knowledgesys1.find.actions;

import java.sql.Connection;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	public EntityImpl() throws Exception {}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		List list = new ArrayList();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT DISTINCT action FROM knowledge");
		while(rs.next()) list.add(rs.getString(1));
		rs.close();
		st.close();
		return list;
	}
}
