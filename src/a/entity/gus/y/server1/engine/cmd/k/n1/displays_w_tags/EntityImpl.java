package a.entity.gus.y.server1.engine.cmd.k.n1.displays_w_tags;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260506";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgedb1.knowledge.displays.w_tags");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		String tags = (String) obj;
		List tagList = Arrays.asList(tags.split(";"));
		return perform.t(new Object[]{cx(), tagList});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}