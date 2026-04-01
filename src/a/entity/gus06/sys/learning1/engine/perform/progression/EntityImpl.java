package a.entity.gus06.sys.learning1.engine.perform.progression;

import a.framework.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250712";}

	public static final String TABLE_NAME = "questions";
	public static final String COL_STATUS = "status";

	public static final String STATUS_SURE = "SURE";
	public static final String STATUS_OVER = "OVER";
	
	
	public Object t(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		String sql = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COL_STATUS 
		+ " IN (?,?)";
		
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, STATUS_SURE);
		st.setObject(2, STATUS_OVER);
		
		ResultSet rs = st.executeQuery();
		Integer count = rs.next() ? (Integer) rs.getInt(1) : null;
		st.close();
		cx.close();
		return count;
	}
}