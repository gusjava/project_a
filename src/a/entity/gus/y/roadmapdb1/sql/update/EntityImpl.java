package a.entity.gus.y.roadmapdb1.sql.update;

import java.sql.Connection;
import java.sql.Statement;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260411";}

	private Service buildJson;

	public EntityImpl() throws Exception {
		buildJson = Outside.service(this, "gus.x.json.build1");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		Connection cx = (Connection) o[0];
		String sql = (String) o[1];

		Statement st = cx.createStatement();
		int affected = st.executeUpdate(sql);
		st.close();
		return buildJson.t(affected);
	}
}
