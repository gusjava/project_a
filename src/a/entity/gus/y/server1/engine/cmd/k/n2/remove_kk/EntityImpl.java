package a.entity.gus.y.server1.engine.cmd.k.n2.remove_kk;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260426";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgesys1.perform.knowledge_link.remove");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.size() != 2) throw new Exception("Invalid arg number: "+list.size());
		
		Long id1 = Long.parseLong(""+list.get(1));
		Long id2 = Long.parseLong(""+list.get(2));
		
		return perform.t(new Object[]{cx(), id1, id2});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}