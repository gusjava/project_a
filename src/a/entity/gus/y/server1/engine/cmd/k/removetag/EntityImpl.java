package a.entity.gus.y.server1.engine.cmd.k.removetag;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service tagRemove;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		tagRemove       = Outside.service(this, "gus.y.knowledgesys1.perform.tag.remove");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-remove-tag: usage: k-remove-tag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		return tagRemove.t(new Object[]{cx(), table, id, tag});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}