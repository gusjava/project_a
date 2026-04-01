package a.entity.gus06.jdbc.mysql.perform.db.drop;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141009";}


	private Service buildSql;
	private Service executeSql;
	private Service protectedPath;


	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.db.drop");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		protectedPath = Outside.service(this,"gus06.jdbc.mysql.check.protectedpath");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		if(protectedPath.f(dbName)) throw new Exception("Attempt to drop db: "+dbName);
		
		String sql = (String) buildSql.t(dbName);
		executeSql.p(new Object[]{cx,sql});
	}
}
