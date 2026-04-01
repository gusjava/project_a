package a.entity.gus06.jdbc.mysql.perform.counttable.bydb.selection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231217";}


	private Service executeSql;
	private Service buildSql;
	private Service rsToMap;
	

	public EntityImpl() throws Exception
	{
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.info.counttable.bydb.selection");
		rsToMap = Outside.service(this,"gus06.jdbc.resultset.toobjectmap");
	}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		List selection = (List) o[1];
		
		String sql = (String) buildSql.t(selection);
		ResultSet rs = (ResultSet) executeSql.t(new Object[]{cx,sql});
		return rsToMap.t(rs);
	}
}
