package a.entity.gus.y.server1.engine.cmd.k.addlink;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service linkAdd;
	private Service engine;

	public EntityImpl() throws Exception {
		linkAdd         = Outside.service(this, "gus.y.knowledgesys1.perform.link.add");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-add-link: usage: k-add-link <table> <id1> <id2> [type]");
		String table = (String) list.get(0);
		String id1   = (String) list.get(1);
		String id2   = (String) list.get(2);
		String type  = list.size() >= 4 ? (String) list.get(3) : "";
		return linkAdd.t(new Object[]{cx(), table, id1, id2, type});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}