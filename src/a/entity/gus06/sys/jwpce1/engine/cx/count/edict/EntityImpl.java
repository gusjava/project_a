package a.entity.gus06.sys.jwpce1.engine.cx.count.edict;

import a.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250726";}

	public static final String TABLE_NAME = "edict";
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT COUNT(*) FROM " + TABLE_NAME;
		
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		Long count = rs.getLong(1);
		rs.close();
		st.close();
		
		return count;
	}
}