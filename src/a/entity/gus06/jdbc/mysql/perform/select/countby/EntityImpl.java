package a.entity.gus06.jdbc.mysql.perform.select.countby;

import a.framework.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}


	private Service buildSql;
	private Service executeSql;
	private Service rsToMap;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.select.countby");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		rsToMap = Outside.service(this,"gus06.jdbc.resultset.toobjectmap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		String colName = (String) o[2];
		
		String sql = (String) buildSql.t(new String[]{path,colName});
		ResultSet rs = (ResultSet) executeSql.t(new Object[]{cx,sql});
		return rsToMap.t(rs);
	}
}