package a.entity.gus.y.knowledgedb1.knowledge.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge";
	public static final String COL_ID = "id";
	
	private Service rsToMap;

	public EntityImpl() throws Exception
	{
		rsToMap = Outside.service(this,"gus.y.knowledgedb1.util.knowledge.rstomap");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long id = (Long) o[1];

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, id);
		ResultSet rs = st.executeQuery();

		Map data = null;
		if (rs.next()) {data = (Map) rsToMap.t(rs);}
		
		st.close();
		return data;
	}
}
