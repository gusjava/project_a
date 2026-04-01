package a.entity.gus06.jdbc.postgresql.perform.table.describe;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190719";}

	private Service buildSql;
	private Service executeSql;

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.postgresql.sql.table.describe");
		executeSql = Outside.service(this,"gus06.jdbc.postgresql.perform.sqlexecute");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		String sql = (String) buildSql.t(path);
		return executeSql.t(new Object[]{cx,sql});
	}
}