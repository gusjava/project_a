package a.entity.gus.y.server1.engine.cmd.k.n1.delete_t;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260426";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgedb1.todo.delete");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.size() != 1) throw new Exception("Invalid arg number: "+list.size());
		
		Long id = Long.parseLong(""+list.get(1));
		boolean done = perform.f(new Object[]{cx(), id});
		return done ? "done" : "delete failed";
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}
