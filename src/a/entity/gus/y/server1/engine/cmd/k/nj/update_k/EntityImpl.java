package a.entity.gus.y.server1.engine.cmd.k.nj.update_k;

import java.sql.Connection;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgedb1.knowledge.update");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		if(json.isEmpty()) throw new Exception("JSON manquant (utiliser :<json>)");
		perform.p(new Object[]{cx(), json});
		return "update done";
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}