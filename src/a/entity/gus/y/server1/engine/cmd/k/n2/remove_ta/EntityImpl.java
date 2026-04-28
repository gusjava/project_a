package a.entity.gus.y.server1.engine.cmd.k.n2.remove_ta;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260427";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgesys1.perform.tag.remove");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.size()!=2) throw new Exception("Wrong arg number: "+list.size());
		
		Long id = Long.parseLong(""+list.get(0));
		String tag = (String) list.get(1);
		
		return perform.t(new Object[]{cx(), "todo", id, tag});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}