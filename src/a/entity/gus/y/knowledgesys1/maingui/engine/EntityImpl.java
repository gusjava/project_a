package a.entity.gus.y.knowledgesys1.maingui.engine;

import java.sql.Connection;
import java.util.List;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, G, R {
	public String creationDate() {return "20260414";}

	private Service cxMain;
	private Service findallRoots;

	private Connection cx;
	private List roots;

	public EntityImpl() throws Exception {
		cxMain = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		findallRoots = Outside.service(this, "gus.y.knowledgedb1.knowledge.findall.roots");
	}

	public void e() throws Exception {
		cx = (Connection) cxMain.g();
		roots = (List) findallRoots.t(cx);
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
}
