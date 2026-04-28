package a.entity.gus.y.server1.engine.cmd.k.n1.sql_columns;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service findColumns;
	private Service engine;

	public EntityImpl() throws Exception
	{
		findColumns = Outside.service(this, "gus.y.knowledgesys1.find.columns");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.isEmpty()) throw new Exception("k-show: nom de table manquant");
		
		String table = (String) list.get(0);
		return findColumns.t(new Object[]{cx(), table});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}