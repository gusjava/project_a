package a.entity.gus06.y.entitydb1.entity_missing_link.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251111";}

	public static final String TABLE_NAME = "entity_missing_link";

	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_MISSING_LINK = "missing_link";
	public static final String COL_POS = "pos";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		String missingLink = (String) o[2];
		Integer pos = (Integer) o[3];

		try {
			String sql = "INSERT INTO " + TABLE_NAME + " (" 
			+ COL_ENTITY_NAME + "," 
			+ COL_MISSING_LINK + "," 
			+ COL_POS + ") VALUES (?,?,?) ";
			
			executeUpdate(cx, sql, entityName, missingLink, pos);
		}
		catch (SQLException e)
		{throw new Exception("Failed add missing link [" + missingLink + "] to entity [" + entityName + "]", e);}
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException
	{
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
