package a.entity.gus.y.knowledgedb1.spec_rule.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "spec_rule";
	public static final String COL_SPEC_ID = "spec_id";
	public static final String COL_RULE_ID = "rule_id";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long specId = (Long) o[1];

		String sql = "SELECT " + COL_RULE_ID + " FROM " + TABLE_NAME + " WHERE " + COL_SPEC_ID + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, specId);
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next())
			data.add(rs.getLong(COL_RULE_ID));
		st.close();
		return data;
	}
}