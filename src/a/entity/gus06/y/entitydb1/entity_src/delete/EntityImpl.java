package a.entity.gus06.y.entitydb1.entity_src.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20251111";}

	public static final String TABLE_NAME = "entity_src";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";

	public void p(Object obj) throws Exception
	{f(obj);}
		
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length == 2)
		{
			Connection cx = (Connection) o[0];
			String entityName = (String) o[1];
			
			String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ENTITY_NAME + "=?";
			return executeUpdate(cx, sql, entityName);
		}
		else if (o.length == 3)
		{
			Connection cx = (Connection) o[0];
			String entityName = (String) o[1];
			String fileName = (String) o[2];
			
			String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + 
			COL_ENTITY_NAME + "=? AND " + 
			COL_FILE_NAME + "=?";
			
			return executeUpdate(cx, sql, entityName, fileName);
		}
		else throw new Exception("Wrong data number: " + o.length);
	}

	private boolean executeUpdate(Connection cx, String sql, String param) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param);
		int r = st.executeUpdate();
		st.close();
		return r>0;
	}
	
	private boolean executeUpdate(Connection cx, String sql, String param1, String param2) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, param1);
		st.setObject(2, param2);
		int r = st.executeUpdate();
		st.close();
		return r>0;
	}
}