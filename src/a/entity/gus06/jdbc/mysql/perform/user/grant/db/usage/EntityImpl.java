package a.entity.gus06.jdbc.mysql.perform.user.grant.db.usage;

import java.sql.Connection;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150624";}

	
	private Service buildSql;
	private Service executeSql;
	
	public EntityImpl() throws Exception
	{
		buildSql = Outside.service(this,"gus06.jdbc.mysql.sql.user.grant.db.usage");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String database = (String) o[1];
		String user = (String) o[2];
		String host = (String) o[3];
		
		String sql = (String) buildSql.t(new String[]{database,user,host});
		executeSql.p(new Object[]{cx,sql});
	}
}
