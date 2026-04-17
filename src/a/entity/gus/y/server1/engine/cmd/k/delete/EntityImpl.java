package a.entity.gus.y.server1.engine.cmd.k.delete;

import java.sql.Connection;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service sqlDelete;

	public EntityImpl() throws Exception
	{
		knowledgeCx = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		sqlDelete   = Outside.service(this, "gus.y.knowledgedb1.sql.delete");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		String table = (String) payload.get("table");
		String id    = (String) payload.get("id");
		String cmdName = "k-delete-" + table.replace("_", "-");
		if(id == null) throw new Exception(cmdName + ": id manquant");
		String sql = "DELETE FROM " + table + " WHERE id = " + id;
		Connection cx = (Connection) knowledgeCx.g();
		return sqlDelete.t(new Object[]{cx, sql});
	}
}