package a.entity.gus06.jdbc.mysql.perform.db.getlength;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150623";}


	private Service buildSql;
	private Service executeSql;

	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.info.getlength.db");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute.tostring");
	}
	


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		String sql = (String) buildSql.t(dbName);
		return executeSql.t(new Object[]{cx,sql});
	}
}