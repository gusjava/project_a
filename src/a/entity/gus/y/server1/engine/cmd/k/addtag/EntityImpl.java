package a.entity.gus.y.server1.engine.cmd.k.addtag;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service tagAdd;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		tagAdd          = Outside.service(this, "gus.y.knowledgesys1.perform.tag.add");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-add-tag: usage: k-add-tag <table> <id> <tag>");
		String table = (String) list.get(0);
		String id    = (String) list.get(1);
		String tag   = (String) list.get(2);
		return tagAdd.t(new Object[]{cx(), table, id, tag});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}