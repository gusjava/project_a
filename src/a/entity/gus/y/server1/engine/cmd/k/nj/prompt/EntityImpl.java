package a.entity.gus.y.server1.engine.cmd.k.nj.prompt;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260503";}

	private Service prompt;

	public EntityImpl() throws Exception
	{
		prompt = Outside.service(this, "gus.y.knowledgesys1.prompt");
	}

	public Object t(Object obj) throws Exception
	{return prompt.t(obj);}
}