package a.entity.gus.y.roadmapdb1.sql.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData meta = rs.getMetaData();
		int cols = meta.getColumnCount();

		List rows = new ArrayList();
		while (rs.next()) {
			Map row = new LinkedHashMap();
			for (int i = 1; i <= cols; i++)
				row.put(meta.getColumnName(i).toLowerCase(), rs.getObject(i));
			rows.add(row);
		}
		st.close();
		return buildJson.t(rows);
	}
}
