package a.entity.gus.y.server1.engine.cmd.k.n3.add_tk;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service addTK;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		addTK = Outside.service(this, "gus.y.knowledgesys1.perform.todoknowledge.add");
		knowledgeEngine  = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception {
		List list = (List) obj;
		if(list.size() != 3) throw new Exception("wrong arg number: "+list.size());
		
		String idT  = (String) list.get(0);
		String idK = (String) list.get(1);
		String type  = (String) list.get(2);
		
		return addTK.t(new Object[]{cx(), idT, idK, type});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}