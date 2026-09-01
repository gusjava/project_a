package a.entity.gus.y.knowledgedb1.doc_y_spec.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260831";}

	public static final String TABLE_NAME = "doc_y_spec";
	public static final String COL_DOC_Y_ID = "doc_y_id";
	public static final String COL_SPEC_ID = "spec_id";

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Long docYId = (Long) o[1];

		String sql = "SELECT " + COL_SPEC_ID + " FROM " + TABLE_NAME + " WHERE " + COL_DOC_Y_ID + "=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, docYId);
		ResultSet rs = st.executeQuery();

		Set data = new HashSet();
		while (rs.next())
			data.add(rs.getLong(COL_SPEC_ID));
		st.close();
		return data;
	}
}