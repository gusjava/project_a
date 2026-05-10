package a.entity.gus.y.knowledgesys1.find.codes;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	public EntityImpl() throws Exception {}

	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		List codes = new ArrayList();
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery("SELECT DISTINCT code FROM knowledge");
		while(rs.next()) codes.add(rs.getString(1));
		rs.close();
		st.close();
		return codes;
	}
}
