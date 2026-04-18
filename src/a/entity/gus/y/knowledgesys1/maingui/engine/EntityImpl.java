package a.entity.gus.y.knowledgesys1.maingui.engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, G, R {
	public String creationDate() {return "20260414";}

	private Service cxMain;

	private Connection cx;
	private List roots;

	public EntityImpl() throws Exception {
		cxMain = Outside.service(this, "gus.y.knowledgedb1.cx.main");
	}

	public void e() throws Exception {
		cx = (Connection) cxMain.g();
		roots = loadRoots();
		loaded();
	}

	private void loaded() {
		send(this, "loaded()");
	}

	public Object g() throws Exception {
		return roots;
	}

	public Object r(String key) throws Exception {
		if (key.equals("cx")) return cx;
		if (key.equals("keys")) return new String[] {"cx"};
		throw new Exception("Unknown key: " + key);
	}

	private List loadRoots() throws Exception {
		String sql = "SELECT * FROM knowledge WHERE id NOT IN"
				+ " (SELECT DISTINCT id_linked FROM knowledge_link) ORDER BY id";
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List data = new ArrayList();
		while (rs.next())
			data.add(row(rs));
		st.close();
		return data;
	}

	private Map row(ResultSet rs) throws SQLException {
		Map m = new HashMap();
		transfer(m, rs, "id");
		transfer(m, rs, "date_created");
		transfer(m, rs, "date_updated");
		transfer(m, rs, "action");
		transfer(m, rs, "code");
		transfer(m, rs, "object");
		transfer(m, rs, "description");
		transfer(m, rs, "state");
		return m;
	}

	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}

