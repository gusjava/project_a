package a.entity.gus.y.entitydb1.entity_link.delete2.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240112";}

	public static final String TABLE_NAME = "entity_link";

	public static final String COL_LINK = "link";

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Set links = (Set) o[1];

		StringBuffer b = new StringBuffer();
		b.append("DELETE FROM ");
		b.append(TABLE_NAME);
		b.append(" WHERE ");
		b.append(COL_LINK);
		b.append(" IN (");

		int nb = links.size();
		for (int i = 0; i < nb; i++) {
			b.append("?");
			if (i < nb - 1)
				b.append(",");
		}
		b.append(")");

		executeUpdate(cx, b.toString(), new ArrayList(links));
	}

	private void executeUpdate(Connection cx, String sql, List params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++)
			st.setObject(i + 1, params.get(i));
		st.executeUpdate();
		st.close();
	}
}
