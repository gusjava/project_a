package a.entity.gus.y.server1.engine.cmd.k.n2.link_k;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service linkAdd;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		linkAdd         = Outside.service(this, "gus.y.knowledgesys1.perform.link.add");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list.size()!=3) throw new Exception("Wrong arg number: "+list.size());
		
		String id1   = (String) list.get(0);
		String id2   = (String) list.get(1);
		String type  = (String) list.get(2);
		
		return linkAdd.t(new Object[]{cx(), "knowledge", id1, id2, type});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}