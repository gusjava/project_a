package a.entity.gus.y.server1.engine.cmd.k.n0.sql_tables;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service findTables;
	private Service knowledgeEngine;

	public EntityImpl() throws Exception {
		findTables      = Outside.service(this, "gus.y.knowledgesys1.find.tables");
		knowledgeEngine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{return findTables.t(cx());}

	private Connection cx() throws Exception
	{return (Connection) knowledgeEngine.r("cx");}
}