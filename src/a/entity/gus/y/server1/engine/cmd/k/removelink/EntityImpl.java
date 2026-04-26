package a.entity.gus.y.server1.engine.cmd.k.removelink;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service linkRemove;
	private Service engine;

	public EntityImpl() throws Exception {
		linkRemove      = Outside.service(this, "gus.y.knowledgesys1.perform.link.remove");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-remove-link: usage: k-remove-link <table> <id1> <id2>");
		String table = (String) list.get(0);
		String id1   = (String) list.get(1);
		String id2   = (String) list.get(2);
		return linkRemove.t(new Object[]{cx(), table, id1, id2});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}