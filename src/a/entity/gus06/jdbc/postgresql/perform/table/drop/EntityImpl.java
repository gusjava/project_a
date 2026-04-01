package a.entity.gus06.jdbc.postgresql.perform.table.drop;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190726";}


	private Service buildSql;
	private Service executeSql;
	private Service protectedPath;

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.postgresql.sql.table.drop");
		executeSql = Outside.service(this,"gus06.jdbc.postgresql.perform.sqlexecute");
		protectedPath = Outside.service(this,"gus06.jdbc.postgresql.check.protectedpath");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		if(protectedPath.f(path)) throw new Exception("Attempt to drop table: "+path);
		
		String sql = (String) buildSql.t(path);
		executeSql.p(new Object[]{cx,sql});
	}
}