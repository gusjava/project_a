package a.entity.gus.y.server1.engine.cmd.k.n1.info_w_code;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgedb1.knowledge.info.w_code");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		return perform.t(new Object[]{cx(), code});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}