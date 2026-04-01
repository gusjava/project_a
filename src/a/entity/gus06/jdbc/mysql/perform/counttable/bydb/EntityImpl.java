package a.entity.gus06.jdbc.mysql.perform.counttable.bydb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231109";}


	private Service executeSql;
	private Service buildSql;
	private Service rsToMap;
	

	public EntityImpl() throws Exception
	{
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.info.counttable.bydb");
		rsToMap = Outside.service(this,"gus06.jdbc.resultset.toobjectmap");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Connection cx = (Connection) obj;
		
		String sql = (String) buildSql.g();
		ResultSet rs = (ResultSet) executeSql.t(new Object[]{cx,sql});
		return rsToMap.t(rs);
	}
}