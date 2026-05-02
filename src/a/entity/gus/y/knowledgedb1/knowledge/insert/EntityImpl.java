package a.entity.gus.y.knowledgedb1.knowledge.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public static final String TABLE_NAME = "knowledge";
	public static final String COL_DATE_CREATED = "date_created";
	public static final String COL_DATE_UPDATED = "date_updated";
	public static final String COL_CODE = "code";
	public static final String COL_ACTION = "action";
	public static final String COL_OBJECT = "object";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_PREPROCESSOR = "preprocessor";

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		Date now = new Date();
		Object code = data.get(COL_CODE);
		Object action = data.get(COL_ACTION);
		Object object = data.get(COL_OBJECT);
		Object description = data.get(COL_DESCRIPTION);
		Object state = data.get(COL_STATE);
		Object preprocessor = data.get(COL_PREPROCESSOR);

		String sql = "INSERT INTO " + TABLE_NAME + " ("
				+ COL_DATE_CREATED + ", "
				+ COL_DATE_UPDATED + ", "
				+ COL_CODE + ", "
				+ COL_ACTION + ", "
				+ COL_OBJECT + ", "
				+ COL_DESCRIPTION + ", "
				+ COL_STATE + ", "
				+ COL_PREPROCESSOR + ") VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement st = cx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setObject(1, now);
		st.setObject(2, null);
		st.setObject(3, code);
		st.setObject(4, action);
		st.setObject(5, object);
		st.setObject(6, description);
		st.setObject(7, state);
		st.setObject(8, preprocessor);
		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		Long id = null;
		if (rs.next()) id = rs.getLong(1);
		st.close();
		return id;
	}
}
