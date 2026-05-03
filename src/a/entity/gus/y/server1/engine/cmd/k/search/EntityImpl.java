package a.entity.gus.y.server1.engine.cmd.k.search;

import java.sql.Connection;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260425";}

	private Service findSearch;
	private Service engine;

	public EntityImpl() throws Exception
	{
		findSearch = Outside.service(this, "gus.y.knowledgesys1.find.search");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list == null || list.size() < 3) throw new Exception("k-search: usage: k-search <table> <field> <value>");
		
		String table = (String) list.get(0);
		String field = (String) list.get(1);
		String value = (String) list.get(2);
		
		return findSearch.t(new Object[]{cx(), table, field, value});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
}