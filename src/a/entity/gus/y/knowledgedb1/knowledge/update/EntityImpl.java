package a.entity.gus.y.knowledgedb1.knowledge.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260502";}

	public static final String TABLE_NAME = "knowledge";
	public static final String COL_ID = "id";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_CODE = "code";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_PREPROCESSOR = "preprocessor";

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		if(!data.containsKey(COL_ID)) throw new Exception("Id key not found inside map");
		if(data.size()==1) return;

		Object id = data.get(COL_ID);

		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("UPDATE " + TABLE_NAME + " SET ");

		sql.append(COL_DATE_UPDATED).append("=?");
		params.add(new Date());

		String[] cols = {COL_CODE, COL_ACTION, COL_OBJECT, COL_DESCRIPTION, COL_STATE, COL_PREPROCESSOR};
		for (String col : cols)
		{
			if (data.containsKey(col))
			{
				sql.append(", ").append(col).append("=?");
				params.add(data.get(col));
			}
		}

		sql.append(" WHERE ").append(COL_ID).append("=?");
		params.add(id);

		executeUpdate(cx, sql.toString(), params.toArray());
	}

	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			st.setObject(i + 1, params[i]);
		st.executeUpdate();
		st.close();
	}
}