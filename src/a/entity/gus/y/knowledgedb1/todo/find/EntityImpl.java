package a.entity.gus.y.knowledgedb1.todo.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}
	
	private Service rsToMap;

	public EntityImpl() throws Exception
	{
		rsToMap = Outside.service(this,"gus.y.knowledgedb1.util.todo.rstomap");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long id = (Long) o[1];

		String sql = "SELECT * FROM todo WHERE id=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, id);
		ResultSet rs = st.executeQuery();

		Map data = null;
		if (rs.next()) {data = (Map) rsToMap.t(rs);}
		st.close();
		return data;
	}
}
