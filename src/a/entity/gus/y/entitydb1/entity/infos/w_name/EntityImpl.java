package a.entity.gus.y.entitydb1.entity.infos.w_name;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260413";}

	public static final String TABLE_NAME = "entity";
	public static final String COL_NAME = "entity_name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALL_NB = "call_nb";
	public static final String COL_FILE_NB = "file_nb";
	public static final String COL_HASH = "hash";

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);
		
		Connection cx = (Connection) o[0];
		String name = (String) o[1];

		String sql = "SELECT " + COL_NAME + ", " + COL_FEATURES + ", " + COL_CREATION_DATE + ", " + COL_LENGTH + ", " + COL_CALL_NB + ", " + COL_FILE_NB + ", " + COL_HASH + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();

		Map result = null;
		if (rs.next()) {
			result = new LinkedHashMap();
			result.put(COL_NAME, rs.getString(COL_NAME));
			result.put(COL_FEATURES, rs.getString(COL_FEATURES));
			result.put(COL_CREATION_DATE, rs.getTimestamp(COL_CREATION_DATE));
			result.put(COL_LENGTH, rs.getInt(COL_LENGTH));
			result.put(COL_CALL_NB, rs.getInt(COL_CALL_NB));
			result.put(COL_FILE_NB, rs.getInt(COL_FILE_NB));
			result.put(COL_HASH, rs.getString(COL_HASH));
		}
		st.close();
		return result;
	}
}
